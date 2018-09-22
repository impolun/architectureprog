package com.example.sushiuivana.data.database.entity

data class OperatorDbEntity(
        override val id: String,
        val login: String,
        val password: String,
        val name: String
) : DbEntity