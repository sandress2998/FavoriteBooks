package ru.mephi.favoritebooks.model.response

import java.time.LocalDateTime

interface AbstractEntityResponse {
    val id: Long
    val createdAt: LocalDateTime
}