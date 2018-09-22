package com.example.sushiuivana.domain.entity.implementation

class Basket(val products: MutableList<Product> = mutableListOf()) {
    fun clear() = products.clear()

    val count: Int
        get() = products.size

    val sumPrice: Int
        get() = products.fold(0) { sum, product -> sum + product.price}
}