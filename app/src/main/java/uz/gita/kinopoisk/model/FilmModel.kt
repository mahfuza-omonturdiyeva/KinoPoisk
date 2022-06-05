package uz.gita.kinopoisk.model

import uz.gita.kinopoisk.presenter.GetFilmListener

interface FilmModel {

    fun getFilm(id: Int, listener: GetFilmListener)

}