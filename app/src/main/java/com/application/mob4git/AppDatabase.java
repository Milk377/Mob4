package com.application.mob4git;

import android.content.Context;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import androidx.room.Database;


@Database(entities = {RecyclerItem.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract  RecyclerItemDao memoDao();
    private static AppDatabase instance = null;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "memo_Database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
