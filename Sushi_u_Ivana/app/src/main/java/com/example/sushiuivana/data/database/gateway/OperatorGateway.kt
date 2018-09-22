package com.example.sushiuivana.data.database.gateway

import android.content.ContentValues
import com.example.sushiuivana.App
import com.example.sushiuivana.data.database.DatabaseManager
import com.example.sushiuivana.data.database.entity.OperatorDbEntity
import com.example.sushiuivana.data.database.table.OperatorTable

object OperatorGateway : Gateway<OperatorDbEntity> {
    override fun create(entity: OperatorDbEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(OperatorTable.id, entity.id)
            put(OperatorTable.name, entity.name)
            put(OperatorTable.login, entity.login)
            put(OperatorTable.password, entity.password)
        }
        return db.insert(OperatorTable.tableName, null, values).toInt()
    }

    override fun read(id: String): OperatorDbEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                OperatorTable.id, OperatorTable.name, OperatorTable.login, OperatorTable.password
        )
        val selection = "${OperatorTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
                OperatorTable.tableName, projection, selection, selectionArgs,
                null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> OperatorDbEntity(
                        id = getString(getColumnIndexOrThrow(OperatorTable.id)),
                        name = getString(getColumnIndexOrThrow(OperatorTable.name)),
                        login = getString(getColumnIndexOrThrow(OperatorTable.login)),
                        password = getString(getColumnIndexOrThrow(OperatorTable.password))
                )
                else -> null
            }
        }
    }

    override fun update(entity: OperatorDbEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(OperatorTable.id, entity.id)
            put(OperatorTable.name, entity.name)
            put(OperatorTable.login, entity.login)
            put(OperatorTable.password, entity.password)
        }
        val selection = "${OperatorTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(OperatorTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${OperatorTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(OperatorTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<OperatorDbEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                OperatorTable.id, OperatorTable.name, OperatorTable.login, OperatorTable.password
        )
        val cursor = db.query(
                OperatorTable.tableName, projection,
                null, null, null, null, null
        )
        val results = mutableListOf<OperatorDbEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                        OperatorDbEntity(
                                id = getString(getColumnIndexOrThrow(OperatorTable.id)),
                                name = getString(getColumnIndexOrThrow(OperatorTable.name)),
                                login = getString(getColumnIndexOrThrow(OperatorTable.login)),
                                password = getString(getColumnIndexOrThrow(OperatorTable.password))
                        )
                )
            }
        }
        return results
    }
}