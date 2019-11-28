package com.livefm.musicworld.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.livefm.musicworld.R;
import com.livefm.musicworld.utils.PublishDataModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailViewActivity extends AppCompatActivity {
    private TextView publishLbl;
    private TextView summarylbl;
    private ImageView imageView;
    private PublishDataModel dataModel;
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(Window.FEATURE_NO_TITLE, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.details_info);
        //dataModel = (PublishDataModel) getIntent().getSerializableExtra("DataBinding");
        //Intent intent = getIntent();
        publishLbl = (TextView)findViewById(R.id.publishlbl);
        summarylbl = (TextView)findViewById(R.id.summarylbl);
        imageView = (ImageView)findViewById(R.id.imageView);
        updateData(dataModel);

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(PublishDataModel event) {
        updateData(event);
    }

    /**
     * Update view data summary,publish date,album image
     * @param dataModel
     */
    private void updateData(PublishDataModel dataModel){
        if(dataModel!=null){
            if(dataModel.getPublish_date()!=null)
                publishLbl.setText(dataModel.getPublish_date());
            if(dataModel.getSummary()!=null)
                summarylbl.setText(dataModel.getSummary());

            if(dataModel.getImage_url()!=null){
                Glide.with(this)
                        .load(dataModel.getImage_url())
                        .into(imageView);

            }
        }

    }
}
