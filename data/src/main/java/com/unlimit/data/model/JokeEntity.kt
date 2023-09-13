package com.unlimit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Joke")
data class JokeEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val joke:String
)

fun JokeEntity.toData() = JokeModel(
    id = id,
    joke = joke
)
