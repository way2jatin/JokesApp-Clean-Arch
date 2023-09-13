package com.unlimit.data.source.local.base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.unlimit.data.model.JokeEntity

@Dao
interface JokeDao {

    @Query("SELECT * FROM Joke")
    suspend fun getAll(): List<JokeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<JokeEntity>)

    @Query("DELETE FROM Joke WHERE id = :id")
    suspend fun deleteAll(id: Long)

    @Query("SELECT COUNT(*) FROM Joke")
    suspend fun getJokesCount(): Int

}