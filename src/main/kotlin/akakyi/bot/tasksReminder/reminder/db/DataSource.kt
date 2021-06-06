package akakyi.bot.tasksReminder.reminder.db

import java.sql.SQLException

import com.zaxxer.hikari.HikariDataSource

import com.zaxxer.hikari.HikariConfig
import java.sql.Connection


object DataSource {

    private val config = HikariConfig("bot.properties")
    private var ds: HikariDataSource = HikariDataSource(config)

    @get:Throws(SQLException::class)
    val connection: Connection
        get() = ds.connection

}