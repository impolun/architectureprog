package com.example.sushiuivana.domain.repository.implementation

import com.example.sushiuivana.data.database.gateway.ProductGateway
import com.example.sushiuivana.domain.entity.implementation.Product
import com.example.sushiuivana.domain.mapper.ProductMapper
import com.example.sushiuivana.domain.repository.base.ProductRepository

class ProductRepositoryImpl : ProductRepository {
    fun create(name: String, price: Int): String {
        val entity = Product(name = name, price = price)
        add(entity)
        return entity.id
    }

    override fun add(entity: Product) {
        ProductGateway.create(ProductMapper.transform(entity))
    }

    override fun readAll(): List<Product> {
        return ProductMapper.transform(ProductGateway.readAll()).toList()
    }

    override fun read(id: String): Product? {
        return ProductGateway.read(id)?.let { ProductMapper.transform(it) }
    }

    override fun delete(entity: Product) {
        ProductGateway.delete(entity.id)
    }

    override fun update(entity: Product) {
        ProductGateway.update(ProductMapper.transform(entity))
    }
}