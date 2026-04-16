package com.example.userlist.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.userlist.db.UserDatabase
import java.io.File

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val dbDir = File(System.getProperty("user.home"), ".userlist").also { it.mkdirs() }
        val dbFile = File(dbDir, "userlist.db")
        val dbExists = dbFile.exists()
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbFile.absolutePath}")
        if (!dbExists) {
            UserDatabase.Schema.create(driver)
        }
        return driver
    }
}
