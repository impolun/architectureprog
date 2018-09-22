package com.example.sushiuivana.domain.repository.base

import com.example.sushiuivana.domain.entity.implementation.Courier
import com.example.sushiuivana.domain.entity.implementation.Operator
import com.example.sushiuivana.domain.entity.implementation.Order
import com.example.sushiuivana.domain.entity.implementation.Product

typealias OrderDetails = Triple<Operator?, Courier?, List<Product>>

interface OrderRepository : Repository<Order>