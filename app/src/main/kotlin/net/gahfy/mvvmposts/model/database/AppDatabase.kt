package net.gahfy.mvvmposts.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.gahfy.mvvmposts.model.Character
import net.gahfy.mvvmposts.model.CharacterDAO

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDAO
}