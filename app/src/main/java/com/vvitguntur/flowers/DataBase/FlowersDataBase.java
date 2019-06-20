package com.vvitguntur.flowers.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vvitguntur.flowers.Models.FlowersPojo;

@Database(entities = {FlowersPojo.class},version = 1,exportSchema = false)
public abstract class FlowersDataBase extends RoomDatabase {
    public abstract FlowersDao flowersDao();
    private static volatile FlowersDataBase INSTANCE;

    static FlowersDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FlowersDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FlowersDataBase.class, "word_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
