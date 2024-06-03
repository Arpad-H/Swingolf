package com.example.swingolf.dataModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import javax.inject.Singleton;

@Database(entities = {Player.class, Game.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerInDao playerInDao();
    public abstract GameInDao gameInDao();
}
