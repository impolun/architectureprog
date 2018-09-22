package com.example.sushiuivana.domain.mapper

import com.example.sushiuivana.data.database.entity.ClientDbEntity
import com.example.sushiuivana.domain.entity.implementation.Client

object ClientMapper : Mapper<Client, ClientDbEntity> {
    override fun transform(data: Client) = ClientDbEntity(
            id = data.id,
            name = data.name,
            login = data.login,
            password = data.password
    )

    override fun transform(data: ClientDbEntity) = Client(
            id = data.id,
            name = data.name,
            login = data.login,
            password = data.password
    )

    override fun transform(collection: Collection<ClientDbEntity>) =
            collection.map { transform(it) }
}