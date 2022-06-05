package uz.gita.kinopoisk.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.kinopoisk.R
import uz.gita.kinopoisk.data.model.data.Film
import uz.gita.kinopoisk.databinding.ItemFilmBinding

class FilmAdapter :
    ListAdapter<Film, FilmAdapter.FilmHolder>(object : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id && oldItem.localized_name == newItem.localized_name
        }
    }) {

    private var onclickItemListener: ((Int) -> Unit)? = null

    fun setOnclickItemListener(listener: (Int) -> Unit) = listener.also { onclickItemListener = it }

    inner class FilmHolder(private val itemFilmBinding: ItemFilmBinding) :
        RecyclerView.ViewHolder(itemFilmBinding.root) {

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                itemFilmBinding.tvLocaleNameFilm.text = this.localized_name
                Glide.with(itemFilmBinding.imgFilm)
                    .load(this.image_url)
                    .centerCrop()
                    .placeholder(R.drawable.film_not_found)
                    .into(itemFilmBinding.imgFilm)
                itemFilmBinding.root.setOnClickListener {
                    onclickItemListener?.let {
                        it.invoke(this.id)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder =
        FilmHolder(ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind()
    }

}