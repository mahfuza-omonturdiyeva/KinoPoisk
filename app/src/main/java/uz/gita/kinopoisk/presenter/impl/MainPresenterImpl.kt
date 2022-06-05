package uz.gita.kinopoisk.presenter.impl

import android.util.Log
import uz.gita.kinopoisk.model.MainModel
import uz.gita.kinopoisk.presenter.ApiListener
import uz.gita.kinopoisk.presenter.MainPresenter
import uz.gita.kinopoisk.view.screen.MainView

class MainPresenterImpl(
    private val model: MainModel
) : MainPresenter {
    private var view: MainView? = null

    override fun onAttachView(view: MainView) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun init() {
        model.init(this)
    }

    override fun joinGenres() {
        view?.showGenres(model.getGenres())
    }

    override fun joinFilmsFromGenre(genre: String) {
        view?.showFilmsFromGenre(model.getFilmsFromGenre(genre))
    }

    override fun joinFilms() {
        view?.showFilmsFromGenre(model.getFilms())
    }

    override fun openFilm(id: Int) {
        view?.navigateToFilm(id)
    }

    override fun onSuccess() {
        view?.init()
    }

    override fun onError(message: String) {
        view?.showToast(message)
    }

    override fun onFailure(e: Throwable) {
        Log.d("error", "onFailure: ${e.message.toString()} ")
    }
}