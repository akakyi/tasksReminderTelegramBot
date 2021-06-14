package akakyi.bot.tasksReminder.reminder.db

import java.sql.SQLException

import com.zaxxer.hikari.HikariDataSource

import com.zaxxer.hikari.HikariConfig
import java.sql.Connection
import java.util.*


object DataSource {

    private val config: HikariConfig
    private val ds: HikariDataSource

    init {
        val properties = getDbProperties()
        config = HikariConfig(properties)
        ds = HikariDataSource(config)
    }

    @get:Throws(SQLException::class)
    val connection: Connection
        get() = ds.connection

    private fun getDbProperties(): Properties {
        val classLoader = Thread.currentThread().javaClass.classLoader
        val resourceAsStream = classLoader.getResourceAsStream("bot.properties")
        val properties = Properties()
        properties.load(resourceAsStream)
        return properties
    }

}