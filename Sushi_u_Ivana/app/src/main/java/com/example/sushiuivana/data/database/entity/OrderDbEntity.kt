package com.example.sushiuivana.data.database.entity

data class OrderDbEntity(
        override val id: String,
        val status: Int,
        val operatorId: String? = null,
        val courierId: String? = null
) : DbEntity