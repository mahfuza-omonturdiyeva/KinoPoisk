package uz.gita.kinopoisk.presenter

import uz.gita.kinopoisk.view.screen.FilmView

interface FilmPresenter:GetFilmListener {

    fun onAttachView(view: FilmView)

    fun onDetachView()

    fun joinFilm(id: Int)

    fun onBack()

}