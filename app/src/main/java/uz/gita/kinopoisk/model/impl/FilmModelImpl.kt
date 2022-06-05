package uz.gita.kinopoisk.model.impl

import uz.gita.kinopoisk.model.FilmModel
import uz.gita.kinopoisk.presenter.GetFilmListener
import uz.gita.kinopoisk.repository.Repository
import uz.gita.kinopoisk.repository.impl.RepositoryImpl

class FilmModelImpl : FilmModel {

    private val repository: Repository = RepositoryImpl

    override fun getFilm(id: Int, listener: GetFilmListener) = repository.getFilm(id, listener)


}