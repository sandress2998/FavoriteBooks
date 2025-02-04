package ru.mephi.favoritebooks.model.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mephi.favoritebooks.database.dao.BookDao
import ru.mephi.favoritebooks.database.dao.GenreDao
import ru.mephi.favoritebooks.database.dao.ReaderDao
import ru.mephi.favoritebooks.model.exception.NotFoundException
import ru.mephi.favoritebooks.model.mapper.BookMapper
import ru.mephi.favoritebooks.model.mapper.GenreMapper
import ru.mephi.favoritebooks.model.mapper.ReaderMapper
import ru.mephi.favoritebooks.model.request.ReaderRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.response.ReaderResponse
import ru.mephi.favoritebooks.model.service.ReaderService

@Service
class ReaderServiceImpl: ReaderService {
    @Autowired
    private lateinit var genreDao: GenreDao

    @Autowired
    private lateinit var readerDao: ReaderDao

    @Autowired
    private lateinit var bookDao: BookDao

    @Autowired
    private lateinit var bookMapper: BookMapper

    @Autowired
    private lateinit var genreMapper: GenreMapper

    @Autowired
    private lateinit var readerMapper: ReaderMapper

    override fun get(id: Long): ReaderResponse {
        val reader = readerDao.findReaderById(id)
        if (reader != null) {
            return readerMapper.asResponse(reader)
        }
        throw NotFoundException("Reader", id)
    }

    @Transactional
    @Modifying
    override fun update(id: Long, request: ReaderRequest): ReaderResponse {
        val foundReader = readerDao.findReaderById(id)
        if (foundReader != null) {
            val updatedReader = readerMapper.update(foundReader, request)
            return readerMapper.asResponse(updatedReader)
        }
        throw NotFoundException("Reader", id)
    }

    @Transactional
    @Modifying
    override fun delete(id: Long) {
        val reader = readerDao.findReaderById(id)
        if (reader != null) {
            reader.favoriteBooks.forEach { it.appreciatingReaders.remove(reader) }
            reader.favoriteGenres.forEach { it.appreciatingReaders.remove(reader) }

            readerDao.deleteById(id)
            return
        }
        throw NotFoundException("Reader", id)
    }

    @Transactional
    @Modifying
    override fun create(request: ReaderRequest): ReaderResponse {
        val createdReader = readerDao.save(readerMapper.asEntity(request))
        return readerMapper.asResponse(createdReader)
    }

    override fun findAll(): Iterable<ReaderResponse> {
        return readerMapper.asResponseList(readerDao.findAll())
    }

    override fun findFavoriteBooks(id: Long): Iterable<BookResponse> {
        val reader = readerDao.findReaderById(id)
        if (reader != null) {
            return bookMapper.asResponseList(reader.favoriteBooks)
        }
        throw NotFoundException("Reader", id)
    }

    override fun findFavoriteGenres(id: Long): Iterable<GenreResponse> {
        val reader = readerDao.findReaderById(id)
        if (reader != null) {
            return genreMapper.asResponseList(reader.favoriteGenres)
        }
        throw NotFoundException("Reader", id)
    }

    @Transactional
    @Modifying
    override fun buyBook(readerId: Long, bookId: Long): BookResponse {
        val reader = readerDao.findReaderById(readerId)
        if (reader != null) {
            val book = bookDao.findBookById(bookId)
            if (book != null) {
                book.sold += 1
                return bookMapper.asResponse(book)
            }
            throw NotFoundException("Book", bookId)
        }
        throw NotFoundException("Reader", readerId)
    }

    @Transactional
    @Modifying
    override fun addFavoriteBook(readerId: Long, bookId: Long): ReaderResponse {
        val reader = readerDao.findReaderById(readerId)
        if (reader != null) {
            val book = bookDao.findBookById(bookId)
            if (book != null) {
                reader.favoriteBooks.add(book)
                book.appreciatingReaders.add(reader)
                return readerMapper.asResponse(reader)
            }
            throw NotFoundException("Book", bookId)
        }
        throw NotFoundException("Reader", readerId)
    }

    @Transactional
    @Modifying
    override fun addFavoriteGenre(readerId: Long, genreId: Long): ReaderResponse {
        val reader = readerDao.findReaderById(readerId)
        if (reader != null) {
            val genre = genreDao.findGenreById(genreId)
            if (genre != null) {
                reader.favoriteGenres.add(genre)
                genre.appreciatingReaders.add(reader)
                return readerMapper.asResponse(reader)
            }
            throw NotFoundException("Genre", genreId)
        }
        throw NotFoundException("Reader", readerId)
    }
}