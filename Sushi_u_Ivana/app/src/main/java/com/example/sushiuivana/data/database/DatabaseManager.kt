package com.example.sushiuivana.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sushiuivana.data.database.table.*

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db ?: return

        db.execSQL(ClientTable.createTableSql)
        db.execSQL(CourierTable.createTableSql)
        db.execSQL(OperatorTable.createTableSql)
        db.execSQL(ProductTable.createTableSql)
        db.execSQL(OrderTable.createTableSql)
        db.execSQL(OrderProductTable.createTableSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db ?: return

        db.execSQL(ClientTable.dropTableSql)
        db.execSQL(CourierTable.dropTableSql)
        db.execSQL(OperatorTable.dropTableSql)
        db.execSQL(ProductTable.dropTableSql)
        db.execSQL(OrderTable.dropTableSql)
        db.execSQL(OrderProductTable.dropTableSql)

        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "sushi.db"
    }
}