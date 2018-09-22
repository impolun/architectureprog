package com.example.sushiuivana.domain.repository.implementation

import com.example.sushiuivana.data.database.gateway.CourierGateway
import com.example.sushiuivana.domain.entity.implementation.Courier
import com.example.sushiuivana.domain.mapper.CourierMapper
import com.example.sushiuivana.domain.repository.base.CourierRepository

class CourierRepositoryImpl : CourierRepository {
    fun create(name: String, login: String, password: String): String {
        val entity = Courier(name = name, login = login, password = password)
        add(entity)
        return entity.id
    }

    override fun add(entity: Courier) {
        CourierGateway.create(CourierMapper.transform(entity))
    }

    override fun readAll(): List<Courier> {
        return CourierMapper.transform(CourierGateway.readAll()).toList()
    }

    override fun read(id: String): Courier? {
        return CourierGateway.read(id)?.let { CourierMapper.transform(it) }
    }

    override fun delete(entity: Courier) {
        CourierGateway.delete(entity.id)
    }

    override fun update(entity: Courier) {
        CourierGateway.update(CourierMapper.transform(entity))
    }
}