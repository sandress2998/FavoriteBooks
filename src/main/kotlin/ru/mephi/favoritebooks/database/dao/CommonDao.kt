package ru.mephi.favoritebooks.database.dao

import jakarta.persistence.MappedSuperclass
import org.springframework.data.repository.CrudRepository
import ru.mephi.favoritebooks.database.entity.AbstractEntity

@MappedSuperclass
interface CommonDao<T: AbstractEntity>: CrudRepository<T, Long>