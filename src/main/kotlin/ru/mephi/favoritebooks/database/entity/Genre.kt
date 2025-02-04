package ru.mephi.favoritebooks.database.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
class Genre (
    @Column
    var name: String,
): AbstractEntity() {
    @Column
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    var books: MutableSet<Book> = mutableSetOf()

    @Column
    @ManyToMany(mappedBy = "favoriteGenres")
    @JsonBackReference
    var appreciatingReaders: MutableSet<Reader> = mutableSetOf()
}