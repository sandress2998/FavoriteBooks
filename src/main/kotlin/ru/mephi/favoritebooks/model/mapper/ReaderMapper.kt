package ru.mephi.favoritebooks.model.mapper

import org.springframework.stereotype.Component
import ru.mephi.favoritebooks.database.entity.Reader
import ru.mephi.favoritebooks.model.request.ReaderRequest
import ru.mephi.favoritebooks.model.response.ReaderResponse

@Component
class ReaderMapper {
    fun asEntity(request: ReaderRequest) = Reader (
        name = request.name,
        surname = request.surname
    )

    fun asResponse(reader: Reader) = ReaderResponse (
        name = reader.name,
        surname = reader.surname,
        favoriteGenres = reader.favoriteGenres,
        favoriteBooks = reader.favoriteBooks,
        id = reader.id,
        createdAt = reader.createdAt
    )

    fun asResponseList(list: Iterable<Reader>): Iterable<ReaderResponse> = list.map {
        ReaderResponse (
            name = it.name,
            surname = it.surname,
            favoriteGenres = it.favoriteGenres,
            favoriteBooks = it.favoriteBooks,
            id = it.id,
            createdAt = it.createdAt
        )
    }

    fun update(reader: Reader, request: ReaderRequest) : Reader {
        reader.name = request.name
        reader.surname = request.surname
        return reader;
    }
}