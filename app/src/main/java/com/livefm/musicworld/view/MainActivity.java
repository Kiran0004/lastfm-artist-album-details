package com.livefm.musicworld.view;


import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.livefm.musicworld.R;
import com.livefm.musicworld.adapter.AlbumDetailsAdapter;
import com.livefm.musicworld.model.AlbumDataModel;
import com.livefm.musicworld.viewModel.AlbumViewModel;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        getAlbumData();
    }

    /**
     * initialization of views and others
     *
     * @param @null
     */
    private void initialization() {
        progress_circular = (ProgressBar) findViewById(R.id.progress_circular);
        my_recycler_view = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        my_recycler_view.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new AlbumDetailsAdapter(this, albumDataModelArrayList);
        my_recycler_view.setAdapter(adapter);

        // View Model
        albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel.class);
    }

    /**
     * get album/artist details from response
     *
     * @param @null
     */
    private void getAlbumData() {
        //articleViewModel.getArticleResponseLiveData().observe();
        albumViewModel.getAlbumResponseLiveData().observe(this, articleResponse -> {
            if (articleResponse != null) {
                progress_circular.setVisibility(View.GONE);
                List<AlbumDataModel> albumDataModels = articleResponse.getResults().getMatchDetails().getAlbumDataModels();
                albumDataModelArrayList.addAll(albumDataModels);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
