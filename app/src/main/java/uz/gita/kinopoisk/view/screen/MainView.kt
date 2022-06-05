package uz.gita.kinopoisk.view.screen

import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.data.model.data.Genre

interface MainView {

    fun init()

    fun showGenres(list: List<Genre>)

    fun showFilmsFromGenre(list: List<Film>)

    fun navigateToFilm(id: Int)

    fun showToast(message: String)
}