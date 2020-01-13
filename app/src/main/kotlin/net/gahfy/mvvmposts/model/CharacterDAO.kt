package net.gahfy.mvvmposts.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CharacterDAO {
    @get: Query("SELECT * FROM character")
    val all: List<Character>

    @Insert
    fun insertAll(vararg character: Character)
}