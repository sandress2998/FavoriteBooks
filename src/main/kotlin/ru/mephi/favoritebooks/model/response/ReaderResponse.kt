package ru.mephi.favoritebooks.model.response

import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.database.entity.Genre
import java.time.LocalDateTime

class ReaderResponse (
    val name: String,

    val surname: String,

    val favoriteGenres: Iterable<Genre>,

    val favoriteBooks: Iterable<Book>,

    override val id: Long,

    override val createdAt: LocalDateTime
): AbstractEntityResponse