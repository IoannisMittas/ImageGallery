package com.mittas.imagegallery.ui.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mittas.imagegallery.R;
import com.mittas.imagegallery.data.ImageModel;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private Context context;
    private List<ImageModel> imageList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public ImageListAdapter(Context context, List<ImageModel> imageList, OnItemClickListener listener) {
        this.context = context;
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageListAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageModel image = imageList.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .override(200, 200);

        Glide.with(context).load(image.getUri())
                .apply(requestOptions)
                .transition(withCrossFade())
                .thumbnail(0.5f)
                .into(holder.imageView);

        holder.itemView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, position);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public void setImages(List<ImageModel> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
