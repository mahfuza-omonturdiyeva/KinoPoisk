package uz.gita.kinopoisk.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.kinopoisk.R
import uz.gita.kinopoisk.data.model.data.Genre
import uz.gita.kinopoisk.databinding.ItemGenreBinding

class GenreAdapter :
    ListAdapter<Genre, GenreAdapter.GenreHolder>(object : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.genre == newItem.genre
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.genre == newItem.genre && oldItem.image_url == newItem.image_url
        }
    }) {

    private var index = -1

    private var onCLickItemListener: ((String) -> Unit)? = null

    fun setOnclickItemListener(listener: (String) -> Unit) =
        listener.also { onCLickItemListener = it }

    fun setIndex(index: Int) {
        this.index = index
    }

    fun getIndex(): Int = index

    inner class GenreHolder(private val itemGenreBinding: ItemGenreBinding) :
        RecyclerView.ViewHolder(itemGenreBinding.root) {
        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                itemGenreBinding.tvGenre.text = this.genre
                Glide
                    .with(itemGenreBinding.imgGenre)
                    .load(this.image_url)
                    .centerCrop()
                    .placeholder(R.drawable.ganre_not_found)
                    .into(itemGenreBinding.imgGenre)
                val genre = this.genre
                itemGenreBinding.root.apply {
                    alpha = if (index == absoluteAdapterPosition) {
                        onCLickItemListener?.invoke(genre)
                        0.2f
                    } else 1f
                }
                itemGenreBinding.root.setOnClickListener {
                    index = absoluteAdapterPosition
                    notifyDataSetChanged()
                    onCLickItemListener?.invoke(this.genre)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder =
        GenreHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: GenreHolder, position: Int) = holder.bind()
}