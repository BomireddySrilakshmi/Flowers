package com.vvitguntur.flowers.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vvitguntur.flowers.DetailsActivity;
import com.vvitguntur.flowers.Models.FlowersPojo;
import com.vvitguntur.flowers.R;

import java.util.ArrayList;
import java.util.List;

public class FlowersAdapter extends RecyclerView.Adapter<FlowersAdapter.ImageHolder> {
    List<FlowersPojo> pojo1s;
    Context context;
    public FlowersAdapter(Activity context) {
        pojo1s= new ArrayList<>();
        this.context=context;
    }
    public void setFlowers(List<FlowersPojo> pojo1s) {
        this.pojo1s .addAll(pojo1s);
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View textView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_view_flower, parent, false);

        return new ImageHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        FlowersPojo pj=pojo1s.get(position);
        String s=pj.getPreviewurl();
        Picasso.get().load(s)
                .into(holder.flowerimage);
        holder.flowerimage.setTag(position);
    }

    @Override
    public int getItemCount() {
        return pojo1s.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView flowerimage;
        public ImageHolder(View itemView) {
            super(itemView);
            this.flowerimage =  itemView.findViewById(R.id.flower);
            flowerimage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FlowersPojo pojo1=pojo1s.get(position);
            Intent intent=new Intent(context,DetailsActivity.class);
            intent.putExtra("details",pojo1);
            context.startActivity(intent);
        }
    }
}
