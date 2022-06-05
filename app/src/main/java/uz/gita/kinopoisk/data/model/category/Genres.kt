package uz.gita.kinopoisk.data.model.category

fun getCategories(): List<String> {
    val list = ArrayList<String>()
    list.add("Драма")
    list.add("Фэнтези")
    list.add("Криминал")
    list.add("Детектив")
    list.add("Мелодрама")
    list.add("Биография")
    list.add("Комедия")
    list.add("Фантастика")
    list.add("Боевик")
    list.add("Триллер")
    list.add("Мюзикл")
    list.add("Приключения")
    list.add("Ужасы")
    return list
}