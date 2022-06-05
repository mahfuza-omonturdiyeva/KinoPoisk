package uz.gita.kinopoisk.presenter

interface ApiListener {

    fun onSuccess()

    fun onError(message: String)

    fun onFailure(e: Throwable)

}