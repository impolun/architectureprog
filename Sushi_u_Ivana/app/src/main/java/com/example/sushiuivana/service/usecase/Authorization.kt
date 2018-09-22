package com.example.sushiuivana.service.usecase

import com.example.sushiuivana.domain.repository.implementation.ClientRepositoryImpl
import com.example.sushiuivana.domain.repository.implementation.CourierRepositoryImpl
import com.example.sushiuivana.domain.repository.implementation.OperatorRepositoryImpl

class Authorization {
    fun registerClient(name: String, login: String, password: String) =
            ClientRepositoryImpl().create(name, login, password)

    fun registerOperator(name: String, login: String, password: String) =
            OperatorRepositoryImpl().create(name, login, password)

    fun registerCourier(name: String, login: String, password: String) =
            CourierRepositoryImpl().create(name, login, password)

    fun loginClient(login: String, password: String) =
            ClientRepositoryImpl().readAll().find { it.login == login && it.password == password }

    fun loginOperator(login: String, password: String) =
            OperatorRepositoryImpl().readAll().find { it.login == login && it.password == password }

    fun loginCourier(login: String, password: String) =
            CourierRepositoryImpl().readAll().find { it.login == login && it.password == password }
}