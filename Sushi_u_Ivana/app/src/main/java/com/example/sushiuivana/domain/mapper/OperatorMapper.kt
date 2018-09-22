package com.example.sushiuivana.domain.mapper

import com.example.sushiuivana.data.database.entity.OperatorDbEntity
import com.example.sushiuivana.domain.entity.implementation.Operator

object OperatorMapper : Mapper<Operator, OperatorDbEntity> {
    override fun transform(data: Operator) = OperatorDbEntity(
            id = data.id,
            name = data.name,
            login = data.login,
            password = data.password
    )

    override fun transform(data: OperatorDbEntity) = Operator(
            id = data.id,
            name = data.name,
            login = data.login,
            password = data.password
    )

    override fun transform(collection: Collection<OperatorDbEntity>) =
            collection.map { transform(it) }
}