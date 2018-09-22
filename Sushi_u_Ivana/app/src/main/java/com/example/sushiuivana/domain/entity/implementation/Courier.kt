package com.example.sushiuivana.domain.entity.implementation

import com.example.sushiuivana.domain.entity.base.Entity
import com.example.sushiuivana.domain.entity.base.UserEntity


data class Courier(
        override val id: String = Entity.generateId(),
        override val login: String,
        override val password: String,
        override val name: String
) : UserEntity