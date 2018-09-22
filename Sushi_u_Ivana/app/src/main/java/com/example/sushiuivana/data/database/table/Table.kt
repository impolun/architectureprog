package com.example.sushiuivana.data.database.table

import android.provider.BaseColumns

const val ID_COLUMN_NAME = BaseColumns._ID

interface Table {
    val tableName: String
    val id: String
    val createTableSql: String
    val dropTableSql: String
}