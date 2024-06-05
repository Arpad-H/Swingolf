package com.example.swingolf.dataModel;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;




import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

//@Module
//@InstallIn(SingletonComponent.class)
public  class DatabaseModule {
static AppDatabase appDatabase;
   // @Provides
  //  @Singleton
   private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}