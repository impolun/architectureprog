package com.example.sushiuivana.domain.repository.base

import com.example.sushiuivana.domain.entity.base.Entity

interface Repository<T : Entity> {
    fun add(entity: T)

    fun readAll(): List<T>

    fun read(id: String): T?

    fun delete(entity: T)

    fun update(entity: T)
}