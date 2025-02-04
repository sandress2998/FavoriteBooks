package ru.mephi.favoritebooks.model.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mephi.favoritebooks.database.dao.BookDao
import ru.mephi.favoritebooks.database.dao.GenreDao
import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.model.exception.NotFoundException
import ru.mephi.favoritebooks.model.mapper.BookMapper
import ru.mephi.favoritebooks.model.mapper.GenreMapper
import ru.mephi.favoritebooks.model.request.BookRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.service.BookService

@Service
class BookServiceImpl: BookService {
    @Autowired
    private lateinit var bookDao: BookDao

    @Autowired
    private lateinit var genreDao: GenreDao

    @Autowired
    private lateinit var bookMapper: BookMapper

    @Autowired
    private lateinit var genreMapper: GenreMapper

    override fun get(id: Long): BookResponse {
        val book: Book? = bookDao.findBookById(id)
        if (book != null) {
            return  bookMapper.asResponse(book)
        }
        throw NotFoundException("Book", id)
    }

    @Transactional
    @Modifying
    override fun delete(id: Long) {
        val book = bookDao.findBookById(id)
        if (book != null) {
            book.genres.forEach { it.books.remove(book) }
            book.appreciatingReaders.forEach { it.favoriteBooks.remove(book) }

            bookDao.deleteById(id)
            return
        }
        throw NotFoundException("Book", id)
    }

    @Transactional
    @Modifying
    override fun update(id: Long, request: BookRequest): BookResponse {
        val foundBook = bookDao.findBookById(id)
        if (foundBook != null) {
            val updatedBook = bookMapper.update(foundBook, request)
            return bookMapper.asResponse(updatedBook)
        }
        throw NotFoundException("Book", id)
    }

    @Transactional
    @Modifying
    override fun create(request: BookRequest): BookResponse {
        val createdBook = bookDao.save(bookMapper.asEntity(request))
        return bookMapper.asResponse(createdBook)
    }

    override fun list(): Iterable<BookResponse> {
        val bookList = bookDao.findAll()
        return bookMapper.asResponseList(bookList)
    }

    override fun getGenres(id: Long): Iterable<GenreResponse> {
        val book: Book? = bookDao.findBookById(id)
        if (book != null) {
            return genreMapper.asResponseList(book.genres)
        }
        throw NotFoundException("Book", id)
    }

    @Transactional
    @Modifying
    override fun addGenre(bookId: Long, genreId: Long): BookResponse {
        val book = bookDao.findBookById(bookId)
        if (book != null) {
            val genre = genreDao.findGenreById(genreId)
            if (genre != null) {
                book.genres.add(genre)
                genre.books.add(book)
                return bookMapper.asResponse(book)
            }
            throw NotFoundException("Genre", genreId)
        }
        throw NotFoundException("Book", bookId)
    }
}