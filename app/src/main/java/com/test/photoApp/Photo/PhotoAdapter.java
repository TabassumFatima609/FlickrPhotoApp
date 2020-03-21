package com.test.photoApp.Photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.test.photoApp.R;
import com.test.photoApp.models.HitsItem;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<HitsItem> hitsItems;
    private Context context;

    public PhotoAdapter(Context context, List<HitsItem> hitsItems) {
        this.hitsItems = hitsItems;
        this.context = context;
    }


    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String images =  hitsItems.get(position).getPreviewURL();

        Glide.with(context)
                .load(images)
                .into(holder.ivPhoto);


    }


    @Override
    public int getItemCount() {
        return hitsItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.image);
        }
    }


}
