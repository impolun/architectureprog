package com.example.sushiuivana.service.usecase.client

import com.example.sushiuivana.domain.entity.implementation.Basket
import com.example.sushiuivana.domain.repository.implementation.OrderRepositoryImpl
import com.example.sushiuivana.domain.repository.implementation.ProductRepositoryImpl
import com.example.sushiuivana.random
import java.util.*

class OrderCreation {

    companion object {
        init {
            if (ProductRepositoryImpl().readAll().isEmpty()) generateMenu()
        }

        private fun generateMenu() {
            ProductRepositoryImpl().apply {
                create("сочная криветка", 257)
                create("Фреско", 259)
                create("Кин-дза-дза", 239)
                create("Сливочный лосось", 265)
                create("Калиформния с ципленком", 129)
                create("Калифорния в кунжуте", 185)
                create("Окунь терияки", 185)
            }
        }
    }


    val basket: Basket by lazy { Basket() }

    fun createOrder(block: ((success: Boolean) -> Unit)? = null) {
        try {
            val order = OrderRepositoryImpl().create()
            order.products.addAll(basket.products)
            OrderRepositoryImpl().update(order)
            basket.clear()
            block?.invoke(true)
        } catch (e: Exception) {
            block?.invoke(false)
        }
    }

    fun generateOrder(block: (basket: Basket) -> Unit) {
        val menu = ProductRepositoryImpl().readAll()
        val count = Random().nextInt(2) + 2
        basket.clear()
        for (i in 0..count) basket.products.add(menu.random()!!)
        block(basket)
    }
}