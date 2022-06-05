package uz.gita.kinopoisk.data.source.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import uz.gita.kinopoisk.data.model.response.FilmsResponse

interface Api {

    @GET("/sequeniatesttask/films.json")
    fun getAllProducts(): Call<FilmsResponse>

}