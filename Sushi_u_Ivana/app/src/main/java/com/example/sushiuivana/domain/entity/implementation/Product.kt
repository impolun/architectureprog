package com.example.sushiuivana.domain.entity.implementation

import com.example.sushiuivana.domain.entity.base.Entity

data class Product(
        override val id: String = Entity.generateId(),
        val name: String,
        val price: Int
) : Entity