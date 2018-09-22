package com.example.sushiuivana.domain.entity.base

interface UserEntity : Entity {
    val login: String
    val password: String
    val name: String
}