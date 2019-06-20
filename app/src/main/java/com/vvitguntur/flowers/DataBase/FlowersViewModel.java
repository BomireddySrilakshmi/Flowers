package com.vvitguntur.flowers.DataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.vvitguntur.flowers.Models.FlowersPojo;

import java.util.List;

public class FlowersViewModel extends AndroidViewModel
{
    private  FlowersRepository mflowersRepository;
    private LiveData<List<FlowersPojo>> mAllflowers;
    public FlowersViewModel(@NonNull Application application) {
        super(application);
        mflowersRepository =new FlowersRepository(application);
        mAllflowers = mflowersRepository.getAllFavMovies();
    }
    public FlowersPojo checkMovie(String id)
    {
        return mflowersRepository.check(id);
    }
    public LiveData<List<FlowersPojo>>getAllMovies()
    {
        return mAllflowers;
    }

    public void insert(FlowersPojo word) {
        mflowersRepository.insert(word);
    }

    public void delete(FlowersPojo favMovies){
        mflowersRepository.delete(favMovies);
    }
}
