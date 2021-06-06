package akakyi.bot.tasksReminder.reminder.service

import akakyi.bot.tasksReminder.api.wrapper.MessageContext
import akakyi.bot.tasksReminder.reminder.db.repository.ChatDAO
import kotlinx.coroutines.runBlocking

object ReminderService {

    fun processOnStartChatting(context: MessageContext) {
        runBlocking {
            ChatDAO.saveChatIfNotExist(context.chatId)
        }
    }

}