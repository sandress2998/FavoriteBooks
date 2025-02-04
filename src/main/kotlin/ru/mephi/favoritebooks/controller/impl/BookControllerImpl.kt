package ru.mephi.favoritebooks.controller.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.mephi.favoritebooks.controller.BookController
import ru.mephi.favoritebooks.model.request.BookRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.DeletedResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.service.BookService

@RestController
@RequestMapping("/book")
class BookControllerImpl: BookController {
    @Autowired
    private lateinit var bookService: BookService

    @GetMapping("/{id}")
    override fun get(@PathVariable id: Long): BookResponse {
        return bookService.get(id)
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): DeletedResponse {
        bookService.delete(id)
        return DeletedResponse()
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Long, @RequestBody request: BookRequest): BookResponse {
        return bookService.update(id, request)
    }

    @PostMapping("/")
    override fun create(@RequestBody request: BookRequest): BookResponse {
        return bookService.create(request)
    }

    @GetMapping("/")
    override fun list(): Iterable<BookResponse> {
        return bookService.list()
    }

    @GetMapping("/{id}/genres")
    override fun getGenres(@PathVariable id: Long): Iterable<GenreResponse> {
        return bookService.getGenres(id)
    }

    @PutMapping("/{bookId}/add/genres/{genreId}")
    override fun addGenre(@PathVariable bookId: Long, @PathVariable genreId: Long): BookResponse {
        return bookService.addGenre(bookId, genreId)
    }
}