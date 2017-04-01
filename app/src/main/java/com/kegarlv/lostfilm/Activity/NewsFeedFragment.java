package com.kegarlv.lostfilm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kegarlv.lostfilm.Adapters.RssAdapter;
import com.kegarlv.lostfilm.App;
import com.kegarlv.lostfilm.Model.Item;
import com.kegarlv.lostfilm.Model.RssResponse;
import com.kegarlv.lostfilm.R;
import com.kegarlv.lostfilm.Rest.ApiClient;
import com.kegarlv.lostfilm.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFeedFragment extends android.support.v4.app.Fragment
        implements RssAdapter.OnRssFeedClicked,SwipeRefreshLayout.OnRefreshListener {

    private static RssAdapter rssAdapter;
    private SwipeRefreshLayout swipeRefresh;

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;

    public NewsFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rssAdapter = new RssAdapter(getContext(),this);
        rv.setAdapter(rssAdapter);

        swipeRefresh = ((SwipeRefreshLayout)view.findViewById(R.id.SwipeRefresh));
        swipeRefresh.setOnRefreshListener(this);

        this.onRefresh();

        return view;
    }


    @Override
    public void onClick(Item item) {
        Intent intent = new Intent(getContext(), AboutMovieActivity.class);
        intent.putExtra("Name",item.getTitle());
        intent.putExtra("ImageLink", item.getDescription());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        ApiInterface api = ApiClient.getClient().create(ApiInterface.class);
        api.getFeed().enqueue(new Callback<RssResponse>() {
            @Override
            public void onResponse(Call<RssResponse> call, Response<RssResponse> response) {
                RssResponse rss = response.body();
                for(Item items : rss.getChannel().getItemList()) {
                    rssAdapter.add(items);
                }
            }
            @Override
            public void onFailure(Call<RssResponse> call, Throwable t) {
                Log.d(App.TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), t.getMessage() , Toast.LENGTH_SHORT).show();

            }
        });
        swipeRefresh.setRefreshing(false);
    }
}
