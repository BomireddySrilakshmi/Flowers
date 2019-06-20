package com.vvitguntur.flowers.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.vvitguntur.flowers.Adapters.FlowersAdapter;
import com.vvitguntur.flowers.Loaders.AsynctaskLoader;
import com.vvitguntur.flowers.Models.FlowersPojo;
import com.vvitguntur.flowers.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class Flowers extends Fragment implements LoaderManager.LoaderCallbacks<String>{
    private com.google.android.gms.ads.AdView AdView;
    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flowers, container, false);
        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544~3347511713");
        InputMethodManager inputMethodManager=(InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()&&getActivity()!=null){
            new AsynctaskLoader(getContext());
        }
        else
        {
            AlertDialog.Builder alert= new AlertDialog.Builder(getContext());
            alert.setTitle("NO INTERNET").setMessage("Check your Internet Connection").setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
            new MyFlowers();
        }
        recyclerView = view.findViewById(R.id.recycler_tv);
        getLoaderManager().initLoader(0,null,this);
        AdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            new AsynctaskLoader(getContext());
        }
        else
        {
            AlertDialog.Builder alert= new AlertDialog.Builder(getContext());
            alert.setTitle("NO INTERNET").setMessage("Check your Internet Connection").setPositiveButton("ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
        }

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsynctaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        List<FlowersPojo> list=new ArrayList<>();
        if(data == null)
        {
          return;
        }
        else {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray1 = jsonObject.getJSONArray("hits");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                    String title = jsonObject1.getString("tags");
                    String largeImageURL = jsonObject1.getString("largeImageURL").toString();
                    String poster = jsonObject1.optString("previewURL").toString();
                    String comments = jsonObject1.getString("comments");
                    String likes = jsonObject1.getString("likes");
                    String views = jsonObject1.getString("views");
                    String pageURL = jsonObject1.getString("pageURL").toString();
                    String id = jsonObject1.getString("id");
                    FlowersPojo pj = new FlowersPojo(largeImageURL, title, pageURL, views, comments, poster, likes, id);
                    list.add(pj);
                    layoutManager = new GridLayoutManager(getContext(), 2);
                    layoutManager.setOrientation(GridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    FlowersAdapter recyclerAdapter = new FlowersAdapter(getActivity());
                    recyclerAdapter.setFlowers(list);
                    recyclerView.setAdapter(recyclerAdapter);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
