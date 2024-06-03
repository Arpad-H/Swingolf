package com.example.swingolf.util;

import androidx.room.TypeConverter;

import com.example.swingolf.dataModel.Game;
import com.example.swingolf.dataModel.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class Converters {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Player> stringToPlayerList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Player>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String playerListToString(List<Player> someObjects) {
        return gson.toJson(someObjects);
    }

//    @TypeConverter
//    public static Game stringToGame(String data) {
//        if (data == null) {
//            return null;
//        }
//
//        Type listType = new TypeToken<Game>() {}.getType();
//        return gson.fromJson(data, listType);
//    }
//    @TypeConverter
//    public static String gameToString(Game game) {
//        return gson.toJson(game);
//    }
}
