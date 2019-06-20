package com.vvitguntur.flowers.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.vvitguntur.flowers.NetworkUtils.NetworkClass;

public class AsynctaskLoader extends android.support.v4.content.AsyncTaskLoader
{
    public AsynctaskLoader(Context context) {
        super(context);
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public String loadInBackground() {
        return  NetworkClass.getFlowerInfo();
    }
}
