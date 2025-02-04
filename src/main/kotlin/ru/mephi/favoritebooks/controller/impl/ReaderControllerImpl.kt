package ru.mephi.favoritebooks.controller.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.mephi.favoritebooks.controller.ReaderController
import ru.mephi.favoritebooks.model.request.ReaderRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.DeletedResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.response.ReaderResponse
import ru.mephi.favoritebooks.model.service.ReaderService

@RestController
@RequestMapping("/reader")
class ReaderControllerImpl: ReaderController {
    @Autowired
    private lateinit var readerService: ReaderService

    @GetMapping("/{id}")
    override fun get(@PathVariable id: Long): ReaderResponse {
        return readerService.get(id)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Long, @RequestBody request: ReaderRequest): ReaderResponse {
        return readerService.update(id, request)
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): DeletedResponse {
        readerService.delete(id)
        return DeletedResponse()
    }

    @PostMapping("/")
    override fun create(@RequestBody request: ReaderRequest): ReaderResponse {
        return readerService.create(request)
    }

    @GetMapping("/")
    override fun findAll(): Iterable<ReaderResponse> {
        return readerService.findAll()
    }

    @GetMapping("/{id}/favorite_books")
    override fun findFavoriteBooks(@PathVariable id: Long): Iterable<BookResponse> {
        return readerService.findFavoriteBooks(id)
    }

    @GetMapping("/{id}/favorite_genres")
    override fun findFavoriteGenres(@PathVariable id: Long): Iterable<GenreResponse> {
        return readerService.findFavoriteGenres(id)
    }

    @PutMapping("/{readerId}/buy/{bookId}")
    override fun buyBook(@PathVariable readerId: Long, @PathVariable bookId: Long): BookResponse {
        return readerService.buyBook(readerId, bookId)
    }

    @PutMapping("/{readerId}/favorite_books/{bookId}")
    override fun addFavoriteBook(@PathVariable readerId: Long, @PathVariable bookId: Long): ReaderResponse {
        return readerService.addFavoriteBook(readerId, bookId)
    }

    @PutMapping("/{readerId}/favorite_genres/{genreId}")
    override fun addFavoriteGenre(@PathVariable readerId: Long, @PathVariable genreId: Long): ReaderResponse {
        return readerService.addFavoriteGenre(readerId, genreId)
    }
}