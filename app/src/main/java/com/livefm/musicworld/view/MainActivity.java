package com.livefm.musicworld.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livefm.musicworld.R;
import com.livefm.musicworld.adapter.AlbumDetailsAdapter;
import com.livefm.musicworld.listener.ItemClickListener;
import com.livefm.musicworld.model.AlbumDataModel;
import com.livefm.musicworld.response.DetailsResponse;
import com.livefm.musicworld.response.ServerResponse;
import com.livefm.musicworld.reterofit.ApiRequest;
import com.livefm.musicworld.reterofit.NetworkRequestor;
import com.livefm.musicworld.utils.Constants;
import com.livefm.musicworld.utils.PublishDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kiran on 2019-11-27.
 */

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular;
    private LinearLayoutManager layoutManager;
    private AlbumDetailsAdapter adapter;
    private ArrayList<AlbumDataModel> albumDataModelArrayList = new ArrayList<>();
    private Spinner categorySpinner;
    private String selection = "album";
    private EditText searchText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(Window.FEATURE_NO_TITLE,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initialization();
        updateSelection();
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        searchText = findViewById(R.id.search_text);
        searchButton = findViewById(R.id.search_btn);

        // Initializing a String Array
        String[] categories = new String[]{
                "Album",
                "Artist",
                "Track"
        };

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,categories
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        categorySpinner.setAdapter(spinnerArrayAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = parent.getItemAtPosition(position).toString().toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selection = parent.getItemAtPosition(0).toString().toLowerCase();
            }
        });
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new AlbumDetailsAdapter(this, albumDataModelArrayList);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                updateSelection();
            }
        });
    }

    /**
     * This method is used to update the data with the selected search item
     * Screen will be refreshed with new items.
     */
    private void  updateSelection(){
        showProgress(true);
        // adapter
        adapter = new AlbumDetailsAdapter(this, albumDataModelArrayList);
        my_recycler_view.setAdapter(adapter);
        adapter.setClickListener(this);
        ApiRequest apiRequest =  NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
        Map<String,String> query = new HashMap<>();
        query.put(Constants.METHOD_SEARCH,selection+Constants.SEARCH_KEY);
        query.put(selection,searchText.getText().toString());
        query.put(Constants.API_KEY,NetworkRequestor.API_KEY);
        query.put(Constants.FORMAT_KEY,NetworkRequestor.FORMAT_VAL);
        Call<ServerResponse> response = apiRequest.getAlbumData(query);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                showProgress(false);
                if (response.body() != null && response.body() instanceof ServerResponse) {
                    if(response.body().getResults()!=null){
                        List<AlbumDataModel> albumDataModels = ((ServerResponse)response.body()).getResults().getMatchDetails().getAlbumDataModels();
                        if(albumDataModels!=null) {
                            albumDataModelArrayList.clear();
                            albumDataModelArrayList.addAll(albumDataModels);
                            my_recycler_view.invalidate();
                            adapter.notifyDataSetChanged();
                        }
                    }else{
                        showPopup();
                    }
                }
            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                showProgress(false);
                Log.e(TAG, t.getMessage());
                showPopup();
            }
        });
    }
    private void showProgress(boolean flag) {
        if(flag)
            progress_circular.setVisibility(View.VISIBLE);
        else
            progress_circular.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View view, int position) {
        ApiRequest apiRequest =  NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
        final String imgUrl;
        Map<String,String> query = new HashMap<>();
        query.put(Constants.METHOD_SEARCH,selection+Constants.GET_INFO);
        if(selection.equals(Constants.ALBUM) || selection.equals(Constants.TRACK)){
            query.put(Constants.ARTIST,albumDataModelArrayList.get(position).getArtist());
        }
        query.put(selection,albumDataModelArrayList.get(position).getName());
        query.put(Constants.API_KEY,NetworkRequestor.API_KEY);
        query.put(Constants.FORMAT_KEY,NetworkRequestor.FORMAT_VAL);
        Call<DetailsResponse> response = apiRequest.getDetailsInfo(query);
        imgUrl = albumDataModelArrayList.get(position).getImgdtl().get(2).getUrl();
        response.enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);
                showProgress(false);
                if (response.body() != null && response.body() instanceof DetailsResponse) {
                    if(response.body().getAlbumDataModel()!=null && response.body().getAlbumDataModel().getWikiDetails()!=null){
                        Intent intent = new Intent(MainActivity.this,DetailViewActivity.class);
                        PublishDataModel publishDataModel = new PublishDataModel();
                        publishDataModel.setSummary(response.body().getAlbumDataModel().getWikiDetails().getSummary());
                        publishDataModel.setImage_url(imgUrl);
                        publishDataModel.setPublish_date(response.body().getAlbumDataModel().getWikiDetails().getPublished());
                        intent.putExtra("DataBinding",publishDataModel);
//                        intent.putExtra("img",imgUrl);
//                        intent.putExtra("published",response.body().getAlbumDataModel().getWikiDetails().getPublished());
                        startActivity(intent);
                    }else{
                        showPopup();
                    }

                }else{
                    showPopup();
                }
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                showProgress(false);
                showPopup();
                Log.e(TAG, t.getMessage());
            }
        });
    }

    /**
     * Dialog to diaplay if no data available (no summary/results from server)
     */
    private void showPopup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("No data available")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
