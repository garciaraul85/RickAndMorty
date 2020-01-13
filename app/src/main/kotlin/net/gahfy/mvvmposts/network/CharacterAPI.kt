package net.gahfy.mvvmposts.network

import io.reactivex.Observable
import net.gahfy.mvvmposts.model.Character
import net.gahfy.mvvmposts.model.RickAndMortyResponse
import retrofit2.http.GET

interface CharacterAPI {

    @GET("/api/character")
    fun getAllCharacters(): Observable<RickAndMortyResponse>
}