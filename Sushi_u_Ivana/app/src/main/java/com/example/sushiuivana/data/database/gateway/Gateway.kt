package com.example.sushiuivana.data.database.gateway

import com.example.sushiuivana.data.database.entity.DbEntity

interface Gateway<T : DbEntity> {
    fun create(entity: T): Int

    fun read(id: String): T?

    fun update(entity: T)

    fun delete(id: String)

    fun readAll(): List<T>
}