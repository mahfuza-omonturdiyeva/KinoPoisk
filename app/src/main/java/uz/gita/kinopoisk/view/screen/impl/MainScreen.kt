package uz.gita.kinopoisk.view.screen.impl

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.kinopoisk.R
import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.data.model.data.Genre
import uz.gita.kinopoisk.databinding.ScreenMainBinding
import uz.gita.kinopoisk.model.impl.MainModelImpl
import uz.gita.kinopoisk.presenter.MainPresenter
import uz.gita.kinopoisk.presenter.impl.MainPresenterImpl
import uz.gita.kinopoisk.view.adapter.FilmAdapter
import uz.gita.kinopoisk.view.adapter.GenreAdapter
import uz.gita.kinopoisk.view.screen.MainView

class MainScreen : Fragment(R.layout.screen_main), MainView {
    private var _binding: ScreenMainBinding? = null
    private val binding get() = _binding!!
    private val filmAdapter by lazy { FilmAdapter() }
    private val genreAdapter by lazy { GenreAdapter() }
    private var presenter: MainPresenter = MainPresenterImpl(MainModelImpl())
    private var index = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.onAttachView(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= ScreenMainBinding.bind(view)
        presenter.init()
    }

    private fun showShimmerFilms() {
        binding.shimmerFilms.visibility = View.VISIBLE
        binding.rvContainerFilms.visibility = View.GONE
        binding.shimmerFilms.startShimmer()
    }

    override fun init() {
        binding.apply {
            genreAdapter.setOnclickItemListener {
                showShimmerFilms()
                presenter.joinFilmsFromGenre(it)
            }
            genreAdapter.setIndex(index ?: -1)
            binding.rvContainerGenres.adapter = genreAdapter
            filmAdapter.setOnclickItemListener {
                presenter.openFilm(it)
            }
            binding.rvContainerFilms.adapter = filmAdapter
            rvContainerFilms.layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            rvContainerGenres.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            shimmerGenres.startShimmer()
        }
        presenter.joinFilms()
        presenter.joinGenres()
    }

    override fun showGenres(list: List<Genre>) {
        binding.shimmerGenres.stopShimmer()
        binding.shimmerGenres.visibility = View.GONE
        binding.rvContainerGenres.visibility = View.VISIBLE
        if (index != -1)
            binding.rvContainerGenres.scrollToPosition(index)
        genreAdapter.submitList(list)
        genreAdapter.notifyDataSetChanged()
    }

    override fun showFilmsFromGenre(list: List<Film>) {
        binding.shimmerFilms.stopShimmer()
        binding.shimmerFilms.visibility = View.GONE
        binding.rvContainerFilms.visibility = View.VISIBLE
        filmAdapter.submitList(list)
        filmAdapter.notifyDataSetChanged()
    }

    override fun navigateToFilm(id: Int) {
        findNavController().navigate(MainScreenDirections.actionMainScreenToFilmScreen(id))
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
        presenter.onDetachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("index", genreAdapter.getIndex())
        super.onSaveInstanceState(outState)
    }
}