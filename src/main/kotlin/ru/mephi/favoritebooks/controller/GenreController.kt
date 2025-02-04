package ru.mephi.favoritebooks.controller

import ru.mephi.favoritebooks.model.request.GenreRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.DeletedResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.response.ReaderResponse

interface GenreController {
    fun get(id: Long): GenreResponse
    fun delete(id: Long): DeletedResponse
    fun create(request: GenreRequest): GenreResponse
    fun update(id: Long, request: GenreRequest): GenreResponse

    fun list(): Iterable<GenreResponse>
    fun findAppreciatingReaders(id: Long): Iterable<ReaderResponse>
    fun findRelatedBooks(id: Long): Iterable<BookResponse>
}