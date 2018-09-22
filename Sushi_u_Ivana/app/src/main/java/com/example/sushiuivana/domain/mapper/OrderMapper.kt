package com.example.sushiuivana.domain.mapper

import com.example.sushiuivana.data.database.entity.OrderDbEntity
import com.example.sushiuivana.data.database.table.OrderTable
import com.example.sushiuivana.domain.entity.implementation.*
import com.example.sushiuivana.domain.repository.base.OrderDetails

object OrderMapper : Mapper<Order, OrderDbEntity> {
    override fun transform(data: Order) = OrderDbEntity(
            id = data.id,
            operatorId = data.operator?.id,
            courierId = data.courier?.id,
            status = data.status.transform().code
    )

    override fun transform(data: OrderDbEntity) = throw Exception()

    fun transform(data: OrderDbEntity, details: OrderDetails) = Order(
            id = data.id,
            operator = details.first,
            courier = details.second,
            status = OrderTable.Status.byCode(data.status).transform(),
            products = details.third.toMutableList()
    )

    override fun transform(collection: Collection<OrderDbEntity>) = throw Exception()
    
    private fun OrderStatus.transform() = when (this) {
        OrderStatus.WAIT_CONFIRM -> OrderTable.Status.WAIT_CONFIRM
        OrderStatus.CONFIRMED -> OrderTable.Status.CONFIRMED
        OrderStatus.WAIT_DELIVERY -> OrderTable.Status.WAIT_DELIVERY
        OrderStatus.IN_DELIVERY -> OrderTable.Status.IN_DELIVERY
        OrderStatus.COMPLETE -> OrderTable.Status.COMPLETE
        OrderStatus.CANCELED -> OrderTable.Status.CANCELED
    }

    private fun OrderTable.Status.transform() = when (this) {
        OrderTable.Status.WAIT_CONFIRM -> OrderStatus.WAIT_CONFIRM
        OrderTable.Status.CONFIRMED -> OrderStatus.CONFIRMED
        OrderTable.Status.WAIT_DELIVERY -> OrderStatus.WAIT_DELIVERY
        OrderTable.Status.IN_DELIVERY -> OrderStatus.IN_DELIVERY
        OrderTable.Status.COMPLETE -> OrderStatus.COMPLETE
        OrderTable.Status.CANCELED -> OrderStatus.CANCELED
    }
}