package ru.mephi.favoritebooks.database.dao

import org.springframework.stereotype.Repository
import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.database.entity.Genre
import ru.mephi.favoritebooks.model.request.BookRequest
import ru.mephi.favoritebooks.model.request.GenreRequest

@Repository
interface GenreDao: CommonDao<Genre> {
    fun findGenreById(id: Long): Genre?
}