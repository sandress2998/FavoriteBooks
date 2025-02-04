package ru.mephi.favoritebooks.controller.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.mephi.favoritebooks.controller.GenreController
import ru.mephi.favoritebooks.model.request.GenreRequest
import ru.mephi.favoritebooks.model.response.BookResponse
import ru.mephi.favoritebooks.model.response.DeletedResponse
import ru.mephi.favoritebooks.model.response.GenreResponse
import ru.mephi.favoritebooks.model.response.ReaderResponse
import ru.mephi.favoritebooks.model.service.GenreService

@RestController
@RequestMapping("/genre")
class GenreControllerImpl: GenreController {
    @Autowired
    private lateinit var genreService: GenreService

    @GetMapping("/{id}")
    override fun get(@PathVariable id: Long): GenreResponse {
        return genreService.get(id)
    }

    @DeleteMapping("/{id}")
    override fun delete(@PathVariable id: Long): DeletedResponse {
        genreService.delete(id)
        return DeletedResponse()
    }

    @PostMapping("/")
    override fun create(@RequestBody request: GenreRequest): GenreResponse {
        return genreService.create(request)
    }

    @PutMapping("/{id}")
    override fun update(@PathVariable id: Long, @RequestBody request: GenreRequest): GenreResponse {
        return genreService.update(id, request)    }

    @GetMapping("/")
    override fun list(): Iterable<GenreResponse> {
        return genreService.list()
    }

    @GetMapping("/{id}/appreciating_readers")
    override fun findAppreciatingReaders(@PathVariable id: Long): Iterable<ReaderResponse> {
        return genreService.findAppreciatingReaders(id)
    }

    @GetMapping("/{id}/books")
    override fun findRelatedBooks(@PathVariable id: Long): Iterable<BookResponse> {
        return genreService.findRelatedBooks(id)
    }
}