package com.example.sushiuivana.data.database.gateway

import android.content.ContentValues
import com.example.sushiuivana.App
import com.example.sushiuivana.data.database.DatabaseManager
import com.example.sushiuivana.data.database.entity.ProductDbEntity
import com.example.sushiuivana.data.database.table.ProductTable

object ProductGateway : Gateway<ProductDbEntity> {
    override fun create(entity: ProductDbEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ProductTable.id, entity.id)
            put(ProductTable.name, entity.name)
            put(ProductTable.price, entity.price)
        }
        return db.insert(ProductTable.tableName, null, values).toInt()
    }

    override fun read(id: String): ProductDbEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                ProductTable.id, ProductTable.name, ProductTable.price
        )
        val selection = "${ProductTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
                ProductTable.tableName, projection, selection, selectionArgs,
                null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> ProductDbEntity(
                        id = getString(getColumnIndexOrThrow(ProductTable.id)),
                        name = getString(getColumnIndexOrThrow(ProductTable.name)),
                        price = getInt(getColumnIndexOrThrow(ProductTable.price))
                )
                else -> null
            }
        }
    }

    override fun update(entity: ProductDbEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ProductTable.id, entity.id)
            put(ProductTable.name, entity.name)
            put(ProductTable.price, entity.price)
        }
        val selection = "${ProductTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(ProductTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${ProductTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(ProductTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<ProductDbEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                ProductTable.id, ProductTable.name, ProductTable.price
        )
        val cursor = db.query(
                ProductTable.tableName, projection,
                null, null, null, null, null
        )
        val results = mutableListOf<ProductDbEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                        ProductDbEntity(
                                id = getString(getColumnIndexOrThrow(ProductTable.id)),
                                name = getString(getColumnIndexOrThrow(ProductTable.name)),
                                price = getInt(getColumnIndexOrThrow(ProductTable.price))
                        )
                )
            }
        }
        return results
    }
}