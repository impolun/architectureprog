package com.example.sushiuivana.service.usecase.operator

import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.domain.entity.implementation.OrderStatus
import com.example.sushiuivana.domain.repository.implementation.OrderRepositoryImpl

class OrderManagment {

    fun getAllOrders(block: (orders: List<Order>) -> Unit) {
        block(OrderRepositoryImpl().readAll())
    }

    fun changeOrderStatus(order: Order, newStatus: OrderStatus, block: (() -> Unit)? = null) {
        order.status = newStatus
        OrderRepositoryImpl().update(order)
        block?.invoke()
    }
}