package ru.mephi.favoritebooks.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import ru.mephi.favoritebooks.model.exception.AbstractApiException

@ControllerAdvice
class ExceptionResolver {
    @ExceptionHandler(value = [AbstractApiException::class])
    fun handle(ex: AbstractApiException, request: WebRequest?): ResponseEntity<Pair<HttpStatus, String?>> {
        return ResponseEntity.status(ex.status).body(ex.status to ex.message)
    }
}