package uz.gita.kinopoisk.model.impl

import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.data.model.data.Genre
import uz.gita.kinopoisk.model.MainModel
import uz.gita.kinopoisk.presenter.ApiListener
import uz.gita.kinopoisk.repository.Repository
import uz.gita.kinopoisk.repository.impl.RepositoryImpl

class MainModelImpl : MainModel {
    private val repository: Repository = RepositoryImpl

    override fun getGenres(): List<Genre> = repository.getGenres()

    override fun getFilmsFromGenre(genre: String): List<Film> = repository.getFilmsFromGenre(genre)

    override fun getFilms(): List<Film> = repository.getAllFilms()

    override fun init(listener: ApiListener) = repository.init(listener)

}