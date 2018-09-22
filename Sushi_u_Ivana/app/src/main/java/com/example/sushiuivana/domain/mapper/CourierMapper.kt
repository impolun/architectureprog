package com.example.sushiuivana.domain.mapper

import com.example.sushiuivana.data.database.entity.CourierDbEntity
import com.example.sushiuivana.domain.entity.implementation.Courier

object CourierMapper : Mapper<Courier, CourierDbEntity> {
    override fun transform(data: Courier) = CourierDbEntity(
            id = data.id,
            name = data.name,
            login = data.login,
            password = data.password
    )

    override fun transform(data: CourierDbEntity) = Courier(
            id = data.id,
            name = data.name,
            login = data.login,
            password = data.password
    )

    override fun transform(collection: Collection<CourierDbEntity>) =
            collection.map { transform(it) }
}