package uz.gita.kinopoisk.data.model.data

data class FilmWithGenre(
    val id: Int,
    val localized_name: String,
    val name: String,
    val year: Int,
    val rating: Double? = null,
    val image_url: String? = null,
    val description: String? = null,
    val genres: List<String>
)
