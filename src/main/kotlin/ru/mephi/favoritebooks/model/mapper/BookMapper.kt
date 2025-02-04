package ru.mephi.favoritebooks.model.mapper

import org.springframework.stereotype.Component
import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.model.request.BookRequest
import ru.mephi.favoritebooks.model.response.BookResponse

@Component
class BookMapper {
    fun asEntity(request: BookRequest) = Book (
        title = request.title,
        author = request.author,
    )

    fun asResponse(book: Book) = BookResponse (
        title = book.title,
        author = book.author,
        sold = book.sold,
        genres = book.genres,
        appreciatingReaders = book.appreciatingReaders,
        id = book.id,
        createdAt = book.createdAt
    )

    fun asResponseList(list: Iterable<Book>): Iterable<BookResponse> = list.map {
        BookResponse(
            title = it.title,
            author = it.author,
            sold = it.sold,
            genres = it.genres,
            appreciatingReaders = it.appreciatingReaders,
            id = it.id,
            createdAt = it.createdAt
        )
    }

    fun update(book:Book, request: BookRequest) : Book {
        book.title = request.title
        book.author = request.author
        return book;
    }
}