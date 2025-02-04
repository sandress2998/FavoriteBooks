package ru.mephi.favoritebooks.model.exception

import org.springframework.http.HttpStatus

class OutOfRangeException(rating: Int) : AbstractApiException() {
    override val message: String = "Rating $rating is out of range: " +
        "rating should be between one and five"
    override val status: HttpStatus = HttpStatus.BAD_REQUEST
}