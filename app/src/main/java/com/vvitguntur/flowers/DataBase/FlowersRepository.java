package com.vvitguntur.flowers.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.vvitguntur.flowers.Models.FlowersPojo;

import java.util.List;

public class FlowersRepository {
    private  FlowersDao mflowersDao;
    private  LiveData<List<FlowersPojo>> mAllflowers;
    public FlowersRepository(Application application) {
        FlowersDataBase db = FlowersDataBase.getDatabase(application);
        mflowersDao = db.flowersDao();
        mAllflowers = mflowersDao.getAllData();
    }
    public FlowersPojo check(String id)
    {
        return mflowersDao.check(id);
    }

    public LiveData<List<FlowersPojo>> getAllFavMovies() {
        return mAllflowers;
    }

    public void insert(FlowersPojo fav) {
        new insertAsyncTask(mflowersDao).execute(fav);
    }

    public void delete(FlowersPojo fav) {
        new deleteAsyncTask(mflowersDao).execute(fav);
    }

    private static class insertAsyncTask extends AsyncTask<FlowersPojo, Void, Void> {

        private FlowersDao mAsyncTaskDao;

        insertAsyncTask(FlowersDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FlowersPojo... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<FlowersPojo, Void, Void> {

        private FlowersDao mAsyncTaskDao;

        deleteAsyncTask(FlowersDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FlowersPojo... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
    
}
