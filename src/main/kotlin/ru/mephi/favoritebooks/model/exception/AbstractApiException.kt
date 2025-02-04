package ru.mephi.favoritebooks.model.exception

import org.springframework.http.HttpStatus

abstract class AbstractApiException: Throwable() {
    abstract val status: HttpStatus
}