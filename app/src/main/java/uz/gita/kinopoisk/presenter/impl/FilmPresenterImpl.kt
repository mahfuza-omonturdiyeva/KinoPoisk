package uz.gita.kinopoisk.presenter.impl

import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.model.FilmModel
import uz.gita.kinopoisk.presenter.FilmPresenter
import uz.gita.kinopoisk.presenter.GetFilmListener
import uz.gita.kinopoisk.view.screen.FilmView

class FilmPresenterImpl(
    private val model: FilmModel
) : FilmPresenter {
    private var view: FilmView? = null

    override fun onAttachView(view: FilmView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun joinFilm(id: Int) =
        model.getFilm(id, this)


    override fun onBack() {
        view?.navigateBack()
    }

    override fun onSuccess(film: Film) {
        view?.showFilm(film)
    }
}