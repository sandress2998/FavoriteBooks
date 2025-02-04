package ru.mephi.favoritebooks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FavoriteBooksApplication

fun main(args: Array<String>) {
    runApplication<FavoriteBooksApplication>(*args)
}
