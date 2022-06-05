package uz.gita.kinopoisk.repository

import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.data.model.data.Genre
import uz.gita.kinopoisk.presenter.ApiListener
import uz.gita.kinopoisk.presenter.GetFilmListener

interface Repository {

    fun getGenres(): List<Genre>

    fun getFilmsFromGenre(genre: String): List<Film>

    fun getAllFilms(): List<Film>

    fun getFilm(id: Int, listener: GetFilmListener)

    fun init(listener: ApiListener)
}