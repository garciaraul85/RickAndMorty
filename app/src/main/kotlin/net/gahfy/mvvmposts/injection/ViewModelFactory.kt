package net.gahfy.mvvmposts.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import net.gahfy.mvvmposts.model.database.AppDatabase
import net.gahfy.mvvmposts.ui.characters.CharacterListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "characters").build()
            @Suppress("UNCHECKED_CAST")
            return CharacterListViewModel(db.characterDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}