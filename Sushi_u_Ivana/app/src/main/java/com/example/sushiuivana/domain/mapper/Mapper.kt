package com.example.sushiuivana.domain.mapper

import com.example.sushiuivana.data.database.entity.DbEntity
import com.example.sushiuivana.domain.entity.base.Entity

interface Mapper<T : Entity, R : DbEntity> {
    fun transform(data: T): R

    fun transform(data: R): T

    fun transform(collection: Collection<R>): Collection<T>
}