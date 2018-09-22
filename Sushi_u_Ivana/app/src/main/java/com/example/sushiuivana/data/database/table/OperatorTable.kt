package com.example.sushiuivana.data.database.table

object OperatorTable : Table {
    override val tableName = "operators"
    override val id = ID_COLUMN_NAME

    const val login = "login"
    const val password = "password"
    const val name = "name"

    override val createTableSql: String
        get() = "CREATE TABLE $tableName ($id TEXT PRIMARY KEY, " +
                "$login TEXT NOT NULL, " +
                "$password TEXT NOT NULL, $name TEXT NOT NULL);"

    override val dropTableSql: String
        get() = "DROP TABLE IF EXISTS $tableName"
}