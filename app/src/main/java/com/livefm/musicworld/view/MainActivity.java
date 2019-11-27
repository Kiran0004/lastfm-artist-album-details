package com.livefm.musicworld.view;


import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livefm.musicworld.R;
import com.livefm.musicworld.adapter.AlbumDetailsAdapter;
import com.livefm.musicworld.model.AlbumDataModel;
import com.livefm.musicworld.reterofit.ApiRequest;
import com.livefm.musicworld.viewModel.AlbumViewModel;
import com.livefm.musicworld.viewModel.MyViewModelFactory;

import java.util.ArrayList;
import java.util.List;


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
                getAlbumData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selection = parent.getItemAtPosition(0).toString().toLowerCase();
                //apiRequest = NetworkRequestor.getRetrofitInstance().create(ApiRequest.class);
                updateSelection();
                getAlbumData();
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
       // my_recycler_view.setAdapter(adapter);

        // View Model
     //   albumViewModel = ViewModelProviders.of(this, new MyViewModelFactory(this.getApplication(), selection,"believe")).get(AlbumViewModel.class);

    }

    /**
     * get album/artist details from response
     *
     * @param @null
     */
    private void getAlbumData() {
        //articleViewModel.getArticleResponseLiveData().observe();
        //if(selection.equals("album")){
           // albumViewModel.getAlbumResponseLiveData().removeObservers(this);
            albumViewModel.getAlbumResponseLiveData().observe(this, articleResponse -> {
                if (articleResponse != null) {
                    progress_circular.setVisibility(View.GONE);
                    List<AlbumDataModel> albumDataModels = articleResponse.getResults().getMatchDetails().getAlbumDataModels();
                    if(albumDataModels!=null) {
                        albumDataModelArrayList.addAll(albumDataModels);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
//
//        }else{
////            albumViewModel.getArtistResponseLiveData().removeObservers(this);
//            albumViewModel.getArtistResponseLiveData().observe(this, articleResponse -> {
//                if (articleResponse != null) {
//                    progress_circular.setVisibility(View.GONE);
//                    List<AlbumDataModel> albumDataModels = articleResponse.getResults().getMatchDetails().getAlbumDataModels();
//                    if(albumDataModels!=null) {
//                        albumDataModelArrayList.addAll(albumDataModels);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            });
//
        //}


    }

    private void  updateSelection(){
        // adapter
        adapter = new AlbumDetailsAdapter(this, albumDataModelArrayList);
        my_recycler_view.setAdapter(adapter);
        MyViewModelFactory myViewModelFactory = new MyViewModelFactory(this.getApplication(),selection,"believe");
        // View Model
        albumViewModel = ViewModelProviders.of(this, myViewModelFactory).get(AlbumViewModel.class);

    }
}
