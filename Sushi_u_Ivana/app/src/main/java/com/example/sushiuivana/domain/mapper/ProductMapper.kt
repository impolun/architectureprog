package com.example.sushiuivana.domain.mapper

import com.example.sushiuivana.data.database.entity.ProductDbEntity
import com.example.sushiuivana.domain.entity.implementation.Product

object ProductMapper : Mapper<Product, ProductDbEntity> {
    override fun transform(data: Product) = ProductDbEntity(
            id = data.id,
            name = data.name,
            price = data.price
    )

    override fun transform(data: ProductDbEntity) = Product(
            id = data.id,
            name = data.name,
            price = data.price
    )

    override fun transform(collection: Collection<ProductDbEntity>) =
            collection.map { transform(it) }
}