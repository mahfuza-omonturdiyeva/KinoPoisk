package uz.gita.kinopoisk.model

import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.data.model.data.Genre
import uz.gita.kinopoisk.presenter.ApiListener

interface MainModel {

    fun getGenres(): List<Genre>

    fun getFilmsFromGenre(genre: String): List<Film>

    fun getFilms(): List<Film>

    fun init(listener: ApiListener)

}