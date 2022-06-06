package uz.gita.kinopoisk.view.screen.impl

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import uz.gita.kinopoisk.R
import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.databinding.ScreenFilmBinding
import uz.gita.kinopoisk.model.impl.FilmModelImpl
import uz.gita.kinopoisk.presenter.FilmPresenter
import uz.gita.kinopoisk.presenter.impl.FilmPresenterImpl
import uz.gita.kinopoisk.view.screen.FilmView

class FilmScreen : Fragment(R.layout.screen_film), FilmView {
    private var _binding: ScreenFilmBinding? = null
    private val binding get() = _binding!!
    private val args: FilmScreenArgs by navArgs()
    private var presenter: FilmPresenter = FilmPresenterImpl(FilmModelImpl())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.onAttachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= ScreenFilmBinding.bind(view)
        val id = args.id
        presenter.joinFilm(id)
        binding.imgBack.setOnClickListener {
            presenter.onBack()
        }
    }

    override fun showFilm(film: Film) {
        binding.apply {
            tvLocaleNameFilm.text = film.localized_name
            tvName.text = film.name
            tvYear.text = film.year.toString()
            tvDescription.text = film.description
            val rating = film.rating
            if (rating == rating.toInt().toDouble() && rating != 0.0)
                tvRating.text = rating.toInt().toString()
            else if (rating == 0.0)
                tvRating.text = ""
            else
                tvRating.text = rating.toString()
            Glide
                .with(imgFilm)
                .load(film.image_url)
                .centerCrop()
                .placeholder(R.drawable.film_not_found)
                .into(imgFilm)
        }
    }

    override fun navigateBack() {
        findNavController().navigate(FilmScreenDirections.actionFilmScreenToMainScreen())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
        presenter.onDetachView()
    }
}