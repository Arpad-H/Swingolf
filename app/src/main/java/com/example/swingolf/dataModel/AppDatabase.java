package com.example.swingolf.dataModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.swingolf.util.Converters;

@TypeConverters(Converters.class)
@Database(entities = {Player.class, Game.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlayerInDao playerInDao();
    public abstract GameInDao gameInDao();
}
