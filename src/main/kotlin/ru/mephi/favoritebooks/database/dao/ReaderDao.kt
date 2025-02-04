package ru.mephi.favoritebooks.database.dao

import org.springframework.stereotype.Repository
import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.database.entity.Reader
import ru.mephi.favoritebooks.model.request.BookRequest
import ru.mephi.favoritebooks.model.request.ReaderRequest

@Repository
interface ReaderDao: CommonDao<Reader> {
    fun findReaderById(id: Long): Reader?
}