package com.gguzman.android.ggcoupons;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gguzman on 4/13/16.
 */
public class ChainStoreRecyclerAdapter extends RecyclerView.Adapter<ChainStoreRecyclerAdapter.ViewHolder> {

    private ArrayList<ChainStore> items;
    private int itemLayout;

    public ChainStoreRecyclerAdapter(ArrayList<ChainStore> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        ChainStore item = items.get(position);
        holder.txtCsName.setText(item.getCsName());
        //holder.image.setImageBitmap(null);
        //Picasso.with(holder.image.getContext()).cancelRequest(holder.image);
        //Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView image;
        public TextView txtCsName;

        public ViewHolder(View itemView) {
            super(itemView);

           // image = (ImageView) itemView.findViewById(R.id.image);
            txtCsName = (TextView) itemView.findViewById(R.id.cs_name);
        }
    }
}
