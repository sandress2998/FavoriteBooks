package ru.mephi.favoritebooks.model.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    entityName: String,
    id: Long
): AbstractApiException() {
    override val status = HttpStatus.NOT_FOUND
    override val message: String = "$entityName not found: $id"
}