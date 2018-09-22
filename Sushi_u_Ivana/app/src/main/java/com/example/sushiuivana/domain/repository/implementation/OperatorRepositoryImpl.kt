package com.example.sushiuivana.domain.repository.implementation

import com.example.sushiuivana.data.database.gateway.OperatorGateway
import com.example.sushiuivana.domain.entity.implementation.Operator
import com.example.sushiuivana.domain.mapper.OperatorMapper
import com.example.sushiuivana.domain.repository.base.OperatorRepository

class OperatorRepositoryImpl : OperatorRepository {
    fun create(name: String, login: String, password: String): String {
        val entity = Operator(name = name, login = login, password = password)
        add(entity)
        return entity.id
    }

    override fun add(entity: Operator) {
        OperatorGateway.create(OperatorMapper.transform(entity))
    }

    override fun readAll(): List<Operator> {
        return OperatorMapper.transform(OperatorGateway.readAll()).toList()
    }

    override fun read(id: String): Operator? {
        return OperatorGateway.read(id)?.let { OperatorMapper.transform(it) }
    }

    override fun delete(entity: Operator) {
        OperatorGateway.delete(entity.id)
    }

    override fun update(entity: Operator) {
        OperatorGateway.update(OperatorMapper.transform(entity))
    }
}