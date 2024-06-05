package com.example.swingolf.dataModel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Player implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id = 0;
    public String name;

    public Player(String name) {
        this.name = name;
    }

}
