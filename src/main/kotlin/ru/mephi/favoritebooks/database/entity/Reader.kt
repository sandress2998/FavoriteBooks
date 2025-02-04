package ru.mephi.favoritebooks.database.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
class Reader (
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var surname: String
): AbstractEntity() {
    @Column
    @ManyToMany
    @JoinTable(
        name = "reader_favorite_books",
        joinColumns = [JoinColumn(name = "reader_id")],
        inverseJoinColumns = [JoinColumn(name = "favorite_books_id")]
    )
    @JsonManagedReference
    var favoriteBooks: MutableSet<Book> = mutableSetOf()

    @Column
    @ManyToMany
    @JoinTable(
        name = "reader_favorite_genres",
        joinColumns = [JoinColumn(name = "reader_id")],
        inverseJoinColumns = [JoinColumn(name = "favorite_genre_id")]
    )
    @JsonManagedReference
    var favoriteGenres: MutableSet<Genre> = mutableSetOf()
}



