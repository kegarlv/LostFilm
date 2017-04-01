package com.kegarlv.lostfilm.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.kegarlv.lostfilm.Model.Item;
import com.kegarlv.lostfilm.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Stack;


public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssViewHolder> {


    public interface OnRssFeedClicked {
        void onClick(Item Item);
    }

    private OnRssFeedClicked listener;
    private List<Item> itemList;
    private final Context context;

    public RssAdapter(Context context, OnRssFeedClicked listener) {
        itemList = new Stack<>();
        this.context = context;
        this.listener = listener;
    }

    public void add(Item item) {
        if(!itemList.contains(item))
            itemList.add(item);

        notifyItemChanged(itemList.size());
    }

    @Override
    public RssViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_feed,parent,false);
        return new RssViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RssViewHolder holder, final int position) {
        Context myContext = holder.imgView.getContext();

        String imageLink = "https:" + itemList.get(position).getDescription();

        Picasso.with(myContext)
                .load(imageLink)
                .into(holder.imgView);

        holder.txtView.setText(itemList.get(position).getTitle());
        holder.movieDate.setText(itemList.get(position).getPubDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(itemList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class RssViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView;
        public ImageView imgView;
        public TextView movieDate;
        public RssViewHolder(View itemView) {
            super(itemView);
            txtView = (TextView)itemView.findViewById(R.id.movieName);
            imgView = (ImageView)itemView.findViewById(R.id.ImageView);
            movieDate = (TextView)itemView.findViewById(R.id.movieDate);

        }
    }
}
