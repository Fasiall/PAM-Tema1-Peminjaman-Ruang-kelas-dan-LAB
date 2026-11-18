package com.example.tugas_minggu2.data.local

import app.cash.sqldelight.db.SqlDriver
import com.example.tugas_minggu2.database.AppDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): AppDatabase {
    val driver = driverFactory.createDriver()
    return AppDatabase(driver)
}
