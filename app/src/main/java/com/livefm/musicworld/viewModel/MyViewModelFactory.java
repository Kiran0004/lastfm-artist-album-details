package com.livefm.musicworld.viewModel;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String method;
    private String type;

    public MyViewModelFactory(Application application, String method,String type) {
        mApplication = application;
        this.method = method;
        this.type = type;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AlbumViewModel(mApplication, method,type);
    }

}