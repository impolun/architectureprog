package com.example.sushiuivana.domain.repository.implementation

import com.example.sushiuivana.data.database.entity.OrderDbEntity
import com.example.sushiuivana.data.database.entity.OrderProductDbEntity
import com.example.sushiuivana.data.database.gateway.OrderGateway
import com.example.sushiuivana.data.database.gateway.OrderProductGateway
import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.domain.mapper.OrderMapper
import com.example.sushiuivana.domain.repository.base.OrderDetails
import com.example.sushiuivana.domain.repository.base.OrderRepository

class OrderRepositoryImpl : OrderRepository {

    fun create(): Order {
        val order = Order()
        add(order)
        return order
    }

    override fun add(entity: Order) {
        OrderGateway.create(OrderMapper.transform(entity))
        entity.products.forEach { OrderProductGateway.create(OrderProductDbEntity(orderId = entity.id, productId = it.id)) }
    }

    override fun readAll(): List<Order> {
        return OrderGateway.readAll().map {
            return@map OrderMapper.transform(it, readOrderDetails(it))
        }
    }

    override fun read(id: String): Order? {
        val order = OrderGateway.read(id) ?: return null
        return OrderMapper.transform(order, readOrderDetails(order))
    }

    override fun delete(entity: Order) {
        OrderGateway.delete(entity.id)
        OrderProductGateway
                .readAll()
                .asSequence()
                .filter { it.orderId == entity.id }
                .forEach { OrderProductGateway.delete(it.id) }
    }

    override fun update(entity: Order) {
        delete(entity)
        add(entity)
    }

    private fun readOrderDetails(order: OrderDbEntity): OrderDetails {
        val operator = order.operatorId?.let { OperatorRepositoryImpl().read(it) }
        val courier = order.courierId?.let { CourierRepositoryImpl().read(it) }
        val products = OrderProductGateway
                .readAll()
                .asSequence()
                .filter { it.orderId == order.id }
                .mapNotNull { ProductRepositoryImpl().read(it.productId) }
                .toList()
        return OrderDetails(operator, courier, products)
    }
}