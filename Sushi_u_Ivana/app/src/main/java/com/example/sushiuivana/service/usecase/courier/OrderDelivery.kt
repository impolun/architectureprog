package com.example.sushiuivana.service.usecase.courier

import com.example.sushiuivana.domain.entity.implementation.Courier
import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.domain.entity.implementation.OrderStatus
import com.example.sushiuivana.domain.repository.implementation.OrderRepositoryImpl

class OrderDelivery {

    fun getCourierOrders(courier: Courier, block: (orders: List<Order>) -> Unit) {
        block(OrderRepositoryImpl().readAll().filter {
            it.status == OrderStatus.WAIT_DELIVERY || it.courier?.id == courier.id
        })
    }

    fun changeOrderStatus(order: Order, courier: Courier?, newStatus: OrderStatus, block: (() -> Unit)? = null) {
        order.status = newStatus
        order.courier = courier
        OrderRepositoryImpl().update(order)
        block?.invoke()
    }
}