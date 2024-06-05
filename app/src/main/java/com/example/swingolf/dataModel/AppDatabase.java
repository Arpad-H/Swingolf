package com.example.swingolf.dataModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class, Game.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerInDao playerInDao();
    public abstract GameInDao gameInDao();
}
