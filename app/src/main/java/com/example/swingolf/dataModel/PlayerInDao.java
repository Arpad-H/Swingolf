package com.example.swingolf.dataModel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlayerInDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);

    @Update
    void update(Player player);

    @Delete
    void delete(Player player);

    @Query("SELECT * FROM Player WHERE id = :id")
    Player getPlayerById(int id);

    @Query("SELECT * FROM Player")
    List<Player> getAllPlayers();

    @Query("SELECT * FROM Player WHERE name = :name")
    Player getPlayerByName(String name);

    @Query("SELECT * FROM Player WHERE name LIKE :name")
    List<Player> getPlayersByName(String name);

    @Query("Delete FROM Player WHERE id = :id")
    void deletePlayerById(int id);


}
