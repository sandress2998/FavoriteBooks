package ru.mephi.favoritebooks.model.response

import ru.mephi.favoritebooks.database.entity.Genre
import ru.mephi.favoritebooks.database.entity.Reader
import java.time.LocalDateTime

class BookResponse (
    val author: String,

    val title: String,

    val genres: Iterable<Genre>,

    val sold: Int,

    val appreciatingReaders: Iterable<Reader>,

    override val id: Long,

    override val createdAt: LocalDateTime
): AbstractEntityResponse