package com.vvitguntur.flowers.Fragments;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.vvitguntur.flowers.Adapters.FlowersAdapter;
import com.vvitguntur.flowers.DataBase.FlowersViewModel;
import com.vvitguntur.flowers.Loaders.AsynctaskLoader;
import com.vvitguntur.flowers.Models.FlowersPojo;
import com.vvitguntur.flowers.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyFlowers extends Fragment {
    RecyclerView recyclerView;
    FlowersViewModel mflowersViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_flowers, container, false);
        recyclerView=view.findViewById(R.id.myrecycler_tv);
        mflowersViewModel= ViewModelProviders.of(this).get(FlowersViewModel.class);
        mflowersViewModel.getAllMovies().observe(MyFlowers.this, new Observer<List<FlowersPojo>>() {
            @Override
            public void onChanged( List<FlowersPojo> resultss)
            {
                FlowersAdapter favMovieAdapter=new FlowersAdapter(getActivity());
                favMovieAdapter.setFlowers(resultss);
                recyclerView.setAdapter(favMovieAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            }
        });
        return view;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mflowersViewModel= ViewModelProviders.of(this).get(FlowersViewModel.class);
        mflowersViewModel.getAllMovies().observe(MyFlowers.this, new Observer<List<FlowersPojo>>() {
            @Override
            public void onChanged( List<FlowersPojo> resultss)
            {
                FlowersAdapter favMovieAdapter=new FlowersAdapter(getActivity());
                favMovieAdapter.setFlowers(resultss);
                recyclerView.setAdapter(favMovieAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            }
        });

        }
}
