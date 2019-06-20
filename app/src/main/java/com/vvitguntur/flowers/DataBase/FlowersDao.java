package com.vvitguntur.flowers.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.vvitguntur.flowers.Models.FlowersPojo;

import java.util.List;

@Dao
public interface FlowersDao {
    @Insert
    void insert(FlowersPojo favflowers);

    @Delete
    void delete(FlowersPojo favflowers);

    @Query("Select * from FlowersPojo")
    LiveData<List<FlowersPojo>> getAllData();

    @Query("Select * from FlowersPojo where flowerid = :t")
    FlowersPojo check(String t);
}
