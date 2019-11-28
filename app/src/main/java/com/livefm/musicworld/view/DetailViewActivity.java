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

public class DetailViewActivity extends AppCompatActivity {
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(Window.FEATURE_NO_TITLE, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.details_info);
        Intent intent = getIntent();
        TextView publishLbl = (TextView)findViewById(R.id.publishlbl);
        TextView summarylbl = (TextView)findViewById(R.id.summarylbl);
        if(intent.getStringExtra("published")!=null)
            publishLbl.setText(intent.getStringExtra("published"));
        if(intent.getStringExtra("wiki_summary")!=null)
            summarylbl.setText(intent.getStringExtra("wiki_summary"));
        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        if(intent.getStringExtra("img")!=null){
            Glide.with(this)
                    .load(intent.getStringExtra("img"))
                    .into(imageView);

        }

    }
}
