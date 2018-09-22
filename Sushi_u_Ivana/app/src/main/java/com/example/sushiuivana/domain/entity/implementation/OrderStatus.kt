package com.example.sushiuivana.domain.entity.implementation

enum class OrderStatus(val str: String) {
    WAIT_CONFIRM("Ожидает подтверждения"),
    CONFIRMED("Подтверждено"),
    WAIT_DELIVERY("Ожидает доставки"),
    IN_DELIVERY("Доставляется"),
    COMPLETE("Выполнен"),
    CANCELED("Отменен")
}