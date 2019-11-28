package com.livefm.musicworld.viewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String method;
    private String type;
    private Map<String,String> options;

    public MyViewModelFactory(Application application, Map<String,String> data) {
        mApplication = application;
        //this.method = method;
        this.options = data;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AlbumViewModel(mApplication, options);
    }

}