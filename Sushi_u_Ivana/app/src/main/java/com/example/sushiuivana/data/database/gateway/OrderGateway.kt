package com.example.sushiuivana.data.database.gateway

import android.content.ContentValues
import com.example.sushiuivana.App
import com.example.sushiuivana.data.database.DatabaseManager
import com.example.sushiuivana.data.database.entity.OrderDbEntity
import com.example.sushiuivana.data.database.table.OrderTable

object OrderGateway : Gateway<OrderDbEntity> {
    override fun create(entity: OrderDbEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(OrderTable.id, entity.id)
            put(OrderTable.status, entity.status)
            put(OrderTable.operatorId, entity.operatorId)
            put(OrderTable.courierId, entity.courierId)
        }
        return db.insert(OrderTable.tableName, null, values).toInt()
    }

    override fun read(id: String): OrderDbEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                OrderTable.id, OrderTable.status, OrderTable.operatorId, OrderTable.courierId
        )
        val selection = "${OrderTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
                OrderTable.tableName, projection, selection, selectionArgs,
                null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> OrderDbEntity(
                        id = getString(getColumnIndexOrThrow(OrderTable.id)),
                        status = getInt(getColumnIndexOrThrow(OrderTable.status)),
                        operatorId = getString(getColumnIndexOrThrow(OrderTable.operatorId)),
                        courierId = getString(getColumnIndexOrThrow(OrderTable.courierId))
                )
                else -> null
            }
        }
    }

    override fun update(entity: OrderDbEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(OrderTable.id, entity.id)
            put(OrderTable.status, entity.status)
            put(OrderTable.operatorId, entity.operatorId)
            put(OrderTable.courierId, entity.courierId)
        }
        val selection = "${OrderTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(OrderTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${OrderTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(OrderTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<OrderDbEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                OrderTable.id, OrderTable.status, OrderTable.operatorId, OrderTable.courierId
        )
        val cursor = db.query(
                OrderTable.tableName, projection,
                null, null, null, null, null
        )
        val results = mutableListOf<OrderDbEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                        OrderDbEntity(
                                id = getString(getColumnIndexOrThrow(OrderTable.id)),
                                status = getInt(getColumnIndexOrThrow(OrderTable.status)),
                                operatorId = getString(getColumnIndexOrThrow(OrderTable.operatorId)),
                                courierId = getString(getColumnIndexOrThrow(OrderTable.courierId))
                        )
                )
            }
        }
        return results
    }
}