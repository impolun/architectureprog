package com.example.sushiuivana.domain.entity.implementation

import com.example.sushiuivana.domain.entity.base.Entity


data class Order(
        override val id: String = Entity.generateId(),
        val products: MutableList<Product> = mutableListOf(),
        var status: OrderStatus = OrderStatus.WAIT_CONFIRM,
        var operator: Operator? = null,
        var courier: Courier? = null
) : Entity