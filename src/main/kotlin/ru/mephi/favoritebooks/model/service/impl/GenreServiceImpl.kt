package ru.mephi.favoritebooks.model.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Modifying
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mephi.favoritebooks.database.dao.GenreDao
import ru.mephi.favoritebooks.model.exception.NotFoundException
import ru.mephi.favoritebooks.model.mapper.BookMapper
import ru.mephi.favoritebooks.model.mapper.GenreMapper
import ru.mephi.favoritebooks.model.mapper.ReaderMapper
import ru.mephi.favoritebooks.model.request.GenreRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.response.ReaderResponse
import ru.mephi.favoritebooks.model.service.GenreService

@Service
class GenreServiceImpl: GenreService {
    @Autowired
    private lateinit var genreDao: GenreDao

    @Autowired
    private lateinit var bookMapper: BookMapper

    @Autowired
    private lateinit var genreMapper: GenreMapper

    @Autowired
    private lateinit var readerMapper: ReaderMapper

    override fun get(id: Long): GenreResponse {
        val genre = genreDao.findGenreById(id)
        if (genre != null) {
            return genreMapper.asResponse(genre)
        }
        throw NotFoundException("Genre", id)
    }

    @Transactional
    @Modifying
    override fun delete(id: Long) {
        val genre = genreDao.findGenreById(id)
        if (genre != null) {
            genre.books.forEach { it.genres.remove(genre) }
            genre.appreciatingReaders.forEach { it.favoriteGenres.remove(genre) }

            genreDao.deleteById(id)
            return
        }
        throw NotFoundException("Genre", id)
    }

    @Transactional
    @Modifying
    override fun create(request: GenreRequest): GenreResponse {
        val createdGenre = genreDao.save(genreMapper.asEntity(request))
        return genreMapper.asResponse(createdGenre)
    }

    @Transactional
    @Modifying
    override fun update(id: Long, request: GenreRequest): GenreResponse {
        val foundGenre = genreDao.findGenreById(id)
        if (foundGenre != null) {
            val updatedGenre = genreMapper.update(foundGenre, request)
            return genreMapper.asResponse(updatedGenre)
        }
        throw NotFoundException("Genre", id)
    }

    override fun list(): Iterable<GenreResponse> {
        return genreMapper.asResponseList(genreDao.findAll())
    }

    override fun findAppreciatingReaders(id: Long): Iterable<ReaderResponse> {
        val genre = genreDao.findGenreById(id)
        if (genre != null) {
            return readerMapper.asResponseList(genre.appreciatingReaders)
        }
        throw NotFoundException("Genre", id)
    }

    override fun findRelatedBooks(id: Long): Iterable<BookResponse> {
        val genre = genreDao.findGenreById(id)
        if (genre != null) {
            return bookMapper.asResponseList(genre.books)
        }
        throw NotFoundException("Genre", id)
    }

}