package ru.mephi.favoritebooks.database.dao

import org.springframework.stereotype.Repository
import ru.mephi.favoritebooks.database.entity.Book
import ru.mephi.favoritebooks.model.request.BookRequest

@Repository
interface BookDao: CommonDao<Book> {
    fun findBookById(id: Long): Book?
}