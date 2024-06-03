package com.example.swingolf.dataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


import com.example.swingolf.util.Converters;

import java.util.List;

@Entity
//@TypeConverters(Converters.class)
public class Game {
    @PrimaryKey(autoGenerate = true)
    public int id = 0;
    public String courtName;
    public int numberOfHoles;

    public int maxNumberOfStrokes;

  //  public List<Player> players;

    public Game(String courtName, int numberOfHoles, int maxNumberOfStrokes, List<Player> players) {
        this.courtName = courtName;
        this.numberOfHoles = numberOfHoles;
        this.maxNumberOfStrokes = maxNumberOfStrokes;
       // this.players = players;
    }
    public Game(String courtName, int numberOfHoles, int maxNumberOfStrokes) {
        this.courtName = courtName;
        this.numberOfHoles = numberOfHoles;
        this.maxNumberOfStrokes = maxNumberOfStrokes;
       // this.players = players;
    }
}
