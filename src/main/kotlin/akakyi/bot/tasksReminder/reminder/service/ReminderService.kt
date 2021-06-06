package akakyi.bot.tasksReminder.reminder.service

import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapper
import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapperRegistry
import akakyi.bot.tasksReminder.api.wrapper.WrapperType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object ReminderService {

    val START_MESSAGE = "/start"

    fun askForRemind(wrapper: MessageApiWrapper) {
        GlobalScope.launch {

        }

    }

}