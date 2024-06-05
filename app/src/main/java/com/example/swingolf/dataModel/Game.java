package com.example.swingolf.dataModel;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.swingolf.util.Converters;

import java.io.Serializable;
import java.util.List;

@Entity
@TypeConverters(Converters.class)
public class Game implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;
    public String courtName;
    public int numberOfHoles;

    public int maxNumberOfStrokes;
    public String date;
    public int winner = Integer.MAX_VALUE;
    public int[][] StrokesProSpieler;

    public List<Player> players;

    public Game(String courtName, int numberOfHoles, int maxNumberOfStrokes, String date, List<Player> players) {
        this.courtName = courtName;
        this.numberOfHoles = numberOfHoles;
        this.maxNumberOfStrokes = maxNumberOfStrokes;
        this.date = date;
        this.players = players;
    }
    @Ignore
    public Game(String courtName, int numberOfHoles, int maxNumberOfStrokes, String date) {
        this.courtName = courtName;
        this.numberOfHoles = numberOfHoles;
        this.maxNumberOfStrokes = maxNumberOfStrokes;
        this.date = date;
    }
}
