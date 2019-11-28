package com.livefm.musicworld.view;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livefm.musicworld.R;
import com.livefm.musicworld.adapter.AlbumDetailsAdapter;
import com.livefm.musicworld.model.AlbumDataModel;
import com.livefm.musicworld.response.ServerResponse;
import com.livefm.musicworld.reterofit.ApiRequest;
import com.livefm.musicworld.reterofit.NetworkRequestor;
import com.livefm.musicworld.viewModel.AlbumViewModel;
import com.livefm.musicworld.viewModel.MyViewModelFactory;

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

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView my_recycler_view;
    private ProgressBar progress_circular;
    private LinearLayoutManager layoutManager;
    private AlbumDetailsAdapter adapter;
    private ArrayList<AlbumDataModel> albumDataModelArrayList = new ArrayList<>();
    private AlbumViewModel albumViewModel;
    private Spinner categorySpinner;
    private String selection = "album";
    ApiRequest apiRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        //getAlbumData();
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = parent.getItemAtPosition(position).toString().toLowerCase();
                // apiRequest = NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
                updateSelection();
//                getAlbumData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selection = parent.getItemAtPosition(0).toString().toLowerCase();
                //apiRequest = NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
                updateSelection();
             //   getAlbumData();
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


    }


    private void  updateSelection(){
        // adapter
        adapter = new AlbumDetailsAdapter(this, albumDataModelArrayList);
        my_recycler_view.setAdapter(adapter);
        ApiRequest apiRequest =  NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
        Map<String,String> query = new HashMap<>();
        query.put("method",selection+".search");
        query.put(selection,"believe");
        query.put("api_key",NetworkRequestor.API_KEY);
        query.put("format",NetworkRequestor.FORMAT_VAL);
        Call<ServerResponse> response = apiRequest.getAlbumData(query);
        response.enqueue(new Callback<ServerResponse>() {


            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);
                if (response.body() != null && response.body() instanceof ServerResponse) {
                    List<AlbumDataModel> albumDataModels = ((ServerResponse)response.body()).getResults().getMatchDetails().getAlbumDataModels();
                    if(albumDataModels!=null) {
                        albumDataModelArrayList.clear();
                        albumDataModelArrayList.addAll(albumDataModels);
                        my_recycler_view.invalidate();
                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });;

    }
}
