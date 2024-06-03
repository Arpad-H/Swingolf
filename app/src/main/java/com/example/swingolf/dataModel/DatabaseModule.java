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

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideDatabase(@ApplicationContext Context context) {

        return Room.databaseBuilder(context, AppDatabase.class, "app-database").build();
    }
}