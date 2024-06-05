package com.example.swingolf.dataModel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface GameInDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Game game);

   @Update
    void update(Game game);

   @Delete
    void delete(Game game);

   @Query("SELECT * FROM Game WHERE id = :id")
    Game getGameById(int id);

    @Query("SELECT * FROM Game")
    List<Game> getAllGames();

    @Query("Delete FROM Game WHERE id = :id")
    void deleteGameById(int id);

    @Query("SELECT winner FROM Game WHERE id = :id")
    int getWinner(int id);

    @Query("UPDATE Game SET winner = :winner WHERE id = :id")
    void setWinner(int id, int winner);
}
