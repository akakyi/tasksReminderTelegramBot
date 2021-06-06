package akakyi.bot.tasksReminder.reminder.db.repository

import akakyi.bot.tasksReminder.reminder.db.DataSource
import java.sql.Connection

object ChatDAO {

    //language=SQL
    private val FIND_BY_CHAT_ID = "select count(*) from chat where chat_id = '%s'"

    //language=SQL
    private val SAVE_CHAT = "insert into chat(chat_id) values ('%s')"

    suspend fun saveChatIfNotExist(chatId: String) {
        val connection = DataSource.connection
        val chatExist = this.isChatExist(chatId, connection)
        if (!chatExist) {
            this.saveChat(chatId, connection)
        }
    }

    private fun saveChat(chatId: String, connection: Connection) = connection.use {
        val query = String.format(FIND_BY_CHAT_ID, chatId)
        val statement = it.prepareStatement(query)
        statement.executeUpdate()
    }

    private fun isChatExist(chatId: String, connection: Connection): Boolean = connection.use {
        val query = String.format(FIND_BY_CHAT_ID, chatId)
        val statement = it.prepareStatement(query)
        val result = statement.executeQuery()

        val size = result.getLong(1)
        return size > 0
    }

}