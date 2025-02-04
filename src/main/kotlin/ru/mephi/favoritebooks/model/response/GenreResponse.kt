package ru.mephi.favoritebooks.model.response

import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.database.entity.Reader
import java.time.LocalDateTime

class GenreResponse (
    val name: String,

    val books: Iterable<Book>,

    val appreciatingReaders: Iterable<Reader>,

    override val id: Long,

    override val createdAt: LocalDateTime
): AbstractEntityResponse