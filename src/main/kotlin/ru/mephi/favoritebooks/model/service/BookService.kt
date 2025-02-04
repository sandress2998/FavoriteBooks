package ru.mephi.favoritebooks.model.service

import ru.mephi.favoritebooks.model.request.BookRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.GenreResponse

interface BookService {
    fun get(id: Long): BookResponse

    fun delete(id: Long)

    fun update(id: Long, request: BookRequest): BookResponse

    fun create(request: BookRequest): BookResponse

    fun list(): Iterable<BookResponse>

    fun getGenres(id: Long): Iterable<GenreResponse>

    fun addGenre(bookId: Long, genreId: Long): BookResponse
}