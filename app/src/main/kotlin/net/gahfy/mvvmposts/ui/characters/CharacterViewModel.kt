package net.gahfy.mvvmposts.ui.characters

import android.arch.lifecycle.MutableLiveData
import net.gahfy.mvvmposts.base.BaseViewModel
import net.gahfy.mvvmposts.model.Character

class CharacterViewModel : BaseViewModel() {
    val characterName = MutableLiveData<String>()
    val characterSpecies = MutableLiveData<String>()
    val characterGender = MutableLiveData<String>()
    val characterImage = MutableLiveData<String>()

    fun bind(character: Character) {
        characterName.value = character.name
        characterSpecies.value = character.species
        characterGender.value = character.gender
        characterImage.value = character.image
    }

    fun openUserDetail() {
        println("_xyz characterName.value = " + characterName.value)
    }
}