package uz.gita.kinopoisk.data.model.data

data class Film(
    val id: Int,
    val localized_name: String,
    val name: String,
    val year: Int,
    val rating: Double = 0.0,
    val image_url: String = "",
    val description: String = ""
)