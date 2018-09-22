package com.example.sushiuivana.data.database.entity

import java.util.*

class OrderProductDbEntity(
        override val id: String = UUID.randomUUID().toString(),
        val orderId: String,
        val productId: String
) : DbEntity