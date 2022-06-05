package uz.gita.kinopoisk.view.screen

import uz.gita.kinopoisk.data.model.data.Film

interface FilmView {

    fun showFilm(film: Film)

    fun navigateBack()

}