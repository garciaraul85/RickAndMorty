package net.gahfy.mvvmposts.base

import android.arch.lifecycle.ViewModel
import net.gahfy.mvvmposts.injection.component.DaggerViewModelInjector
import net.gahfy.mvvmposts.injection.component.ViewModelInjector
import net.gahfy.mvvmposts.injection.module.NetworkModule
import net.gahfy.mvvmposts.ui.characters.CharacterListViewModel
import net.gahfy.mvvmposts.ui.characters.CharacterViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CharacterViewModel -> injector.inject(this)
            is CharacterListViewModel -> injector.inject(this)
        }
    }
}