package com.example.sushiuivana.data.database.table

object ProductTable : Table {
    override val tableName = "products"
    override val id = ID_COLUMN_NAME

    const val name = "name"
    const val price = "price"

    override val createTableSql: String
        get() = "CREATE TABLE $tableName ($id TEXT PRIMARY KEY, " +
                "$name TEXT NOT NULL, " +
                "$price INTEGER NOT NULL DEFAULT 0);"

    override val dropTableSql: String
        get() = "DROP TABLE IF EXISTS $tableName"
}