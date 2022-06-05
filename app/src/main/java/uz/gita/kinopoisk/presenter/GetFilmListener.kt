package uz.gita.kinopoisk.presenter

import uz.gita.kinopoisk.data.model.data.Film

interface GetFilmListener {

    fun onSuccess(film: Film)

}