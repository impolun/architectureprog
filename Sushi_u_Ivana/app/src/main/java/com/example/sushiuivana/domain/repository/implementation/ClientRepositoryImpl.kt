package com.example.sushiuivana.domain.repository.implementation

import com.example.sushiuivana.data.database.gateway.ClientGateway
import com.example.sushiuivana.domain.entity.implementation.Client
import com.example.sushiuivana.domain.mapper.ClientMapper
import com.example.sushiuivana.domain.repository.base.ClientRepository

class ClientRepositoryImpl : ClientRepository {
    fun create(name: String, login: String, password: String): String {
        val entity = Client(name = name, login = login, password = password)
        add(entity)
        return entity.id
    }

    override fun add(entity: Client) {
        ClientGateway.create(ClientMapper.transform(entity))
    }

    override fun readAll(): List<Client> {
        return ClientMapper.transform(ClientGateway.readAll()).toList()
    }

    override fun read(id: String): Client? {
        return ClientGateway.read(id)?.let { ClientMapper.transform(it) }
    }

    override fun delete(entity: Client) {
        ClientGateway.delete(entity.id)
    }

    override fun update(entity: Client) {
        ClientGateway.update(ClientMapper.transform(entity))
    }
}