package com.example.sushiuivana.data.database.gateway

import android.content.ContentValues
import com.example.sushiuivana.App
import com.example.sushiuivana.data.database.DatabaseManager
import com.example.sushiuivana.data.database.entity.OrderProductDbEntity
import com.example.sushiuivana.data.database.table.OrderProductTable

object OrderProductGateway : Gateway<OrderProductDbEntity> {
    override fun create(entity: OrderProductDbEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(OrderProductTable.id, entity.id)
            put(OrderProductTable.orderId, entity.orderId)
            put(OrderProductTable.productId, entity.productId)
        }
        return db.insert(OrderProductTable.tableName, null, values).toInt()
    }

    override fun read(id: String): OrderProductDbEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                OrderProductTable.id, OrderProductTable.orderId, OrderProductTable.productId
        )
        val selection = "${OrderProductTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
                OrderProductTable.tableName, projection, selection, selectionArgs,
                null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> OrderProductDbEntity(
                        id = getString(getColumnIndexOrThrow(OrderProductTable.id)),
                        orderId = getString(getColumnIndexOrThrow(OrderProductTable.orderId)),
                        productId = getString(getColumnIndexOrThrow(OrderProductTable.productId))
                )
                else -> null
            }
        }
    }

    override fun update(entity: OrderProductDbEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(OrderProductTable.id, entity.id)
            put(OrderProductTable.orderId, entity.orderId)
            put(OrderProductTable.productId, entity.productId)
        }
        val selection = "${OrderProductTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(OrderProductTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${OrderProductTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(OrderProductTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<OrderProductDbEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                OrderProductTable.id, OrderProductTable.orderId, OrderProductTable.productId
        )
        val cursor = db.query(
                OrderProductTable.tableName, projection,
                null, null, null, null, null
        )
        val results = mutableListOf<OrderProductDbEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                        OrderProductDbEntity(
                                id = getString(getColumnIndexOrThrow(OrderProductTable.id)),
                                orderId = getString(getColumnIndexOrThrow(OrderProductTable.orderId)),
                                productId = getString(getColumnIndexOrThrow(OrderProductTable.productId))
                        )
                )
            }
        }
        return results
    }
}