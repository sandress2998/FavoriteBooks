package ru.mephi.favoritebooks.model.mapper

import org.springframework.stereotype.Component
import ru.mephi.favoritebooks.database.entity.Genre
import ru.mephi.favoritebooks.model.request.GenreRequest
import ru.mephi.favoritebooks.model.response.GenreResponse

@Component
class GenreMapper {
    fun asEntity(request: GenreRequest) = Genre (
        name = request.name
    )

    fun asResponse(genre: Genre) = GenreResponse(
        name = genre.name,
        books = genre.books,
        appreciatingReaders = genre.appreciatingReaders,
        id = genre.id,
        createdAt = genre.createdAt
    )

    fun asResponseList(list: Iterable<Genre>): Iterable<GenreResponse> = list.map {
        GenreResponse(
            name = it.name,
            books = it.books,
            appreciatingReaders = it.appreciatingReaders,
            id = it.id,
            createdAt = it.createdAt
        )
    }

    fun update(genre: Genre, request: GenreRequest) : Genre {
        genre.name = request.name
        return genre;
    }
}