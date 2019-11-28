package com.livefm.musicworld.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.livefm.musicworld.R;
import com.livefm.musicworld.listener.ItemClickListener;
import com.livefm.musicworld.model.AlbumDataModel;

import java.util.ArrayList;

/**
 * Created by Kiran on 2019-11-27.
 */

public class AlbumDetailsAdapter extends RecyclerView.Adapter<AlbumDetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AlbumDataModel> albumDataModelArrayList;
    private ItemClickListener listener;
    public AlbumDetailsAdapter(Context context, ArrayList<AlbumDataModel> albumDataModelArrayList) {
        this.context = context;
        this.albumDataModelArrayList = albumDataModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_albumdetail,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumDetailsAdapter.ViewHolder viewHolder, int i) {
        AlbumDataModel albumDataModel = albumDataModelArrayList.get(i);
        viewHolder.tvTitle.setText(albumDataModel.getName());
        viewHolder.tvDescription.setText(albumDataModel.getArtist());
        Glide.with(context)
                .load(albumDataModel.getImgdtl().get(2).getUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(viewHolder.imgViewCover);
    }


    @Override
    public int getItemCount() {
        return albumDataModelArrayList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imgViewCover;
        private final TextView tvTitle;
        private final TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewCover=(ImageView) itemView.findViewById(R.id.imgViewCover);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitle);
            tvDescription=(TextView) itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) listener.onClick(itemView, getAdapterPosition());
        }
    }
}
