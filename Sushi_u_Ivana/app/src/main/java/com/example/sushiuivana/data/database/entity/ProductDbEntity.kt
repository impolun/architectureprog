package com.example.sushiuivana.data.database.entity

data class ProductDbEntity(
        override val id: String,
        val name: String,
        val price: Int
) : DbEntity