package uz.gita.kinopoisk.presenter

import uz.gita.kinopoisk.view.screen.MainView

interface MainPresenter:ApiListener {

    fun onAttachView(view: MainView)

    fun onDetachView()

    fun init()

    fun joinGenres()

    fun joinFilmsFromGenre(genre: String)

    fun joinFilms()

    fun openFilm(id: Int)

}