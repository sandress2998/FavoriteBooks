package ru.mephi.favoritebooks.database.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
class Book (
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var author: String
) : AbstractEntity() {
    @Column
    @ManyToMany
    @JoinTable(
        name = "book_genre",
        joinColumns = [JoinColumn(name = "book_id")],
        inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    @JsonManagedReference
    var genres: MutableSet<Genre> = mutableSetOf()

    @Column
    @ManyToMany(mappedBy = "favoriteBooks")
    @JsonBackReference
    var appreciatingReaders: MutableSet<Reader> = mutableSetOf()

    @Column(nullable = false)
    var sold: Int = 0
}