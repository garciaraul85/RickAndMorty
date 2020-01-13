package net.gahfy.mvvmposts.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Character(
        @field:PrimaryKey val id: Int,
        val name: String,
        val species: String,
        val gender: String,
        val image: String)