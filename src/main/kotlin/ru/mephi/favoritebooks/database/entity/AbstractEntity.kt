package ru.mephi.favoritebooks.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
}