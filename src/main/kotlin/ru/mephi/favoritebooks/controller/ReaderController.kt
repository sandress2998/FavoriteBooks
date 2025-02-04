package ru.mephi.favoritebooks.controller

import ru.mephi.favoritebooks.model.request.ReaderRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.DeletedResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.response.ReaderResponse

interface ReaderController {
    fun get(id: Long): ReaderResponse
    fun update(id: Long, request: ReaderRequest): ReaderResponse
    fun delete(id: Long): DeletedResponse
    fun create(request: ReaderRequest): ReaderResponse

    fun findAll(): Iterable<ReaderResponse>
    fun findFavoriteBooks(id: Long): Iterable<BookResponse>
    fun findFavoriteGenres(id: Long): Iterable<GenreResponse>
    fun buyBook(readerId: Long, bookId: Long): BookResponse
    fun addFavoriteBook(readerId: Long, bookId: Long): ReaderResponse
    fun addFavoriteGenre(readerId: Long, genreId: Long): ReaderResponse
}