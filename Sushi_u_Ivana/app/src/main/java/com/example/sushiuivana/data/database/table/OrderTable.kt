package com.example.sushiuivana.data.database.table

object OrderTable : Table {
    override val tableName = "orders"
    override val id = ID_COLUMN_NAME

    const val status = "status"
    const val operatorId = "operator_id"
    const val courierId = "courier_id"

    enum class Status(val code: Int) {
        WAIT_CONFIRM(0),
        CONFIRMED(1),
        WAIT_DELIVERY(2),
        IN_DELIVERY(3),
        COMPLETE(4),
        CANCELED(5);

        companion object {
            fun byCode(code: Int) = Status.values().find { it.code == code } ?: WAIT_CONFIRM
        }
    }

    override val createTableSql: String
        get() = "CREATE TABLE $tableName ($id TEXT PRIMARY KEY, " +
                "$status INTEGER NOT NULL DEFAULT ${Status.WAIT_CONFIRM.code}, " +
                "$operatorId TEXT, $courierId TEXT, " +
                "FOREIGN KEY($operatorId) REFERENCES ${OperatorTable.tableName}(${OperatorTable.id}), " +
                "FOREIGN KEY($courierId) REFERENCES ${CourierTable.tableName}(${CourierTable.id}));"

    override val dropTableSql: String
        get() = "DROP TABLE IF EXISTS $tableName"
}