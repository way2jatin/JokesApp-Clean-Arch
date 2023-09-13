package com.unlimit.data.source.local.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unlimit.data.model.JokeEntity
import com.unlimit.data.source.local.base.AppDatabase.Companion.DB_VERSION
import com.unlimit.data.source.local.base.dao.JokeDao

/**
 * To manage data items that can be accessed and updated
 * & also maintain relationships between them
 *
 */
@Database(entities = [JokeEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val jokeDao : JokeDao

    companion object {
        const val DB_NAME = "Jokes.db"
        const val DB_VERSION = 1
    }
}