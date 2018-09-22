package com.example.sushiuivana.data.database.gateway

import android.content.ContentValues
import com.example.sushiuivana.App
import com.example.sushiuivana.data.database.DatabaseManager
import com.example.sushiuivana.data.database.entity.CourierDbEntity
import com.example.sushiuivana.data.database.table.CourierTable

object CourierGateway : Gateway<CourierDbEntity> {
    override fun create(entity: CourierDbEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(CourierTable.id, entity.id)
            put(CourierTable.name, entity.name)
            put(CourierTable.login, entity.login)
            put(CourierTable.password, entity.password)
        }
        return db.insert(CourierTable.tableName, null, values).toInt()
    }

    override fun read(id: String): CourierDbEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                CourierTable.id, CourierTable.name, CourierTable.login, CourierTable.password
        )
        val selection = "${CourierTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
                CourierTable.tableName, projection, selection, selectionArgs,
                null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> CourierDbEntity(
                        id = getString(getColumnIndexOrThrow(CourierTable.id)),
                        name = getString(getColumnIndexOrThrow(CourierTable.name)),
                        login = getString(getColumnIndexOrThrow(CourierTable.login)),
                        password = getString(getColumnIndexOrThrow(CourierTable.password))
                )
                else -> null
            }
        }
    }

    override fun update(entity: CourierDbEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(CourierTable.id, entity.id)
            put(CourierTable.name, entity.name)
            put(CourierTable.login, entity.login)
            put(CourierTable.password, entity.password)
        }
        val selection = "${CourierTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(CourierTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${CourierTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(CourierTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<CourierDbEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                CourierTable.id, CourierTable.name, CourierTable.login, CourierTable.password
        )
        val cursor = db.query(
                CourierTable.tableName, projection,
                null, null, null, null, null
        )
        val results = mutableListOf<CourierDbEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                        CourierDbEntity(
                                id = getString(getColumnIndexOrThrow(CourierTable.id)),
                                name = getString(getColumnIndexOrThrow(CourierTable.name)),
                                login = getString(getColumnIndexOrThrow(CourierTable.login)),
                                password = getString(getColumnIndexOrThrow(CourierTable.password))
                        )
                )
            }
        }
        return results
    }
}