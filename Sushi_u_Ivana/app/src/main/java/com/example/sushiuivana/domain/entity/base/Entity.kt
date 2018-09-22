package com.example.sushiuivana.domain.entity.base

import com.example.sushiuivana.domain.Util

interface Entity {
    val id: String

    companion object {
        fun generateId() = Util.generateID()
    }
}