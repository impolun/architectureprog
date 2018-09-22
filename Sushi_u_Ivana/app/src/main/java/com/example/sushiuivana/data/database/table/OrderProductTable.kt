package com.example.sushiuivana.data.database.table

object OrderProductTable : Table {
    override val tableName = "order_products"
    override val id = ID_COLUMN_NAME

    const val orderId = "order_id"
    const val productId = "product_id"

    override val createTableSql: String
        get() = "CREATE TABLE $tableName ($id TEXT PRIMARY KEY, " +
                "$orderId TEXT NOT NULL, " +
                "$productId TEXT NOT NULL, " +
                "FOREIGN KEY($orderId) REFERENCES ${OrderTable.tableName}(${OrderTable.id}), " +
                "FOREIGN KEY($productId) REFERENCES ${ProductTable.tableName}(${ProductTable.id}));"

    override val dropTableSql: String
        get() = "DROP TABLE IF EXISTS $tableName"
}