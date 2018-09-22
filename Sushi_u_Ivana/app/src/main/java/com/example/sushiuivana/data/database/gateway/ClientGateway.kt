package com.example.sushiuivana.data.database.gateway

import android.content.ContentValues
import com.example.sushiuivana.App
import com.example.sushiuivana.data.database.DatabaseManager
import com.example.sushiuivana.data.database.entity.ClientDbEntity
import com.example.sushiuivana.data.database.table.ClientTable

object ClientGateway : Gateway<ClientDbEntity> {
    override fun create(entity: ClientDbEntity): Int {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ClientTable.id, entity.id)
            put(ClientTable.name, entity.name)
            put(ClientTable.login, entity.login)
            put(ClientTable.password, entity.password)
        }
        return db.insert(ClientTable.tableName, null, values).toInt()
    }

    override fun read(id: String): ClientDbEntity? {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                ClientTable.id, ClientTable.name, ClientTable.login, ClientTable.password
        )
        val selection = "${ClientTable.id} = ?"
        val selectionArgs = arrayOf(id)
        val cursor = db.query(
                ClientTable.tableName, projection, selection, selectionArgs,
                null, null, null
        )
        with(cursor) {
            return when {
                moveToNext() -> ClientDbEntity(
                        id = getString(getColumnIndexOrThrow(ClientTable.id)),
                        name = getString(getColumnIndexOrThrow(ClientTable.name)),
                        login = getString(getColumnIndexOrThrow(ClientTable.login)),
                        password = getString(getColumnIndexOrThrow(ClientTable.password))
                )
                else -> null
            }
        }
    }

    override fun update(entity: ClientDbEntity) {
        val db = DatabaseManager(App.context).writableDatabase
        val values = ContentValues().apply {
            put(ClientTable.id, entity.id)
            put(ClientTable.name, entity.name)
            put(ClientTable.login, entity.login)
            put(ClientTable.password, entity.password)
        }
        val selection = "${ClientTable.id} LIKE ?"
        val selectionArgs = arrayOf(entity.id)
        db.update(ClientTable.tableName, values, selection, selectionArgs)
    }

    override fun delete(id: String) {
        val db = DatabaseManager(App.context).writableDatabase
        val selection = "${ClientTable.id} LIKE ?"
        val selectionArgs = arrayOf(id)
        db.delete(ClientTable.tableName, selection, selectionArgs)
    }

    override fun readAll(): List<ClientDbEntity> {
        val db = DatabaseManager(App.context).readableDatabase
        val projection = arrayOf(
                ClientTable.id, ClientTable.name, ClientTable.login, ClientTable.password
        )
        val cursor = db.query(
                ClientTable.tableName, projection,
                null, null, null, null, null
        )
        val results = mutableListOf<ClientDbEntity>()
        with(cursor) {
            while (moveToNext()) {
                results.add(
                        ClientDbEntity(
                                id = getString(getColumnIndexOrThrow(ClientTable.id)),
                                name = getString(getColumnIndexOrThrow(ClientTable.name)),
                                login = getString(getColumnIndexOrThrow(ClientTable.login)),
                                password = getString(getColumnIndexOrThrow(ClientTable.password))
                        )
                )
            }
        }
        return results
    }
}