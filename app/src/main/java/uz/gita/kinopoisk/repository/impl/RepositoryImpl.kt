package uz.gita.kinopoisk.repository.impl

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.gita.kinopoisk.data.ApiClient
import uz.gita.kinopoisk.data.model.category.getCategories
import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.data.model.data.FilmWithGenre
import uz.gita.kinopoisk.data.model.data.Genre
import uz.gita.kinopoisk.data.model.response.FilmsResponse
import uz.gita.kinopoisk.data.source.remote.api.Api
import uz.gita.kinopoisk.presenter.ApiListener
import uz.gita.kinopoisk.presenter.GetFilmListener
import uz.gita.kinopoisk.repository.Repository

object RepositoryImpl : Repository {
    private val api = ApiClient.retrofit.create(Api::class.java)
    private val listFilms = ArrayList<FilmWithGenre>()

    override fun init(listener: ApiListener) {
        api.getAllProducts().enqueue(object : Callback<FilmsResponse> {
            override fun onResponse(
                call: Call<FilmsResponse>,
                response: Response<FilmsResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("1111", "onResponse: ")
                        listFilms.clear()
                        val sortedlist =
                            it.films.sortedBy { filmWithGenre: FilmWithGenre -> filmWithGenre.localized_name }
                        listFilms.addAll(sortedlist)
                        listener.onSuccess()
                    }
                } else {
                    listener.onError("Oшибка подключения к серверу")
                }
            }
            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                listener.onFailure(t)
            }
        })
    }


    override fun getGenres(): List<Genre> {
        val genres = getCategories() as ArrayList<String>
        val listGenre = ArrayList<Genre>()
        val listAllFilms = ArrayList<FilmWithGenre>()
        listAllFilms.addAll(listFilms)
        for (itemGenre in genres) {
            for (item in listAllFilms) {
                var isFind = false
                for (genre in item.genres) {
                    if (itemGenre.toLowerCase() == genre && !item.image_url.isNullOrEmpty()) {
                        listGenre.add(Genre(item.image_url, itemGenre))
                        listAllFilms.remove(item)
                        isFind = true
                        break
                    }
                }
                if (isFind)
                    break
            }
        }
        return listGenre
    }

    override fun getFilmsFromGenre(genre: String): List<Film> {
        val listFilm = ArrayList<Film>()
        for (item in listFilms) {
            for (itemGenre in item.genres) {
                if (genre.toLowerCase() == itemGenre) {
                    val film = Film(
                        item.id,
                        item.localized_name,
                        item.name,
                        item.year,
                        item.rating ?: 0.0,
                        item.image_url ?: "",
                        item.description ?: ""
                    )
                    listFilm.add(film)
                    break
                }
            }
        }
        return listFilm
    }

    override fun getAllFilms(): List<Film> {
        val list = ArrayList<Film>()
        if (listFilms.isNotEmpty())
            for (item in listFilms) {
                val film = Film(
                    item.id,
                    item.localized_name,
                    item.name,
                    item.year,
                    item.rating ?: 0.0,
                    item.image_url ?: "",
                    item.description ?: ""
                )
                list.add(film)
            }
        return list
    }

    override fun getFilm(id: Int, listener: GetFilmListener) {
        for (item in listFilms) {
            if (item.id == id) {
                val film = Film(
                    item.id,
                    item.localized_name,
                    item.name,
                    item.year,
                    item.rating ?: 0.0,
                    item.image_url ?: "",
                    item.description ?: ""
                )
                listener.onSuccess(film)
                break
            }
        }
    }
}