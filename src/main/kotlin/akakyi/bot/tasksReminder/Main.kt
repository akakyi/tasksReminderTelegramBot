@file:JvmName("Main")

package akakyi.bot.tasksReminder

import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapperRegistry
import akakyi.bot.tasksReminder.api.wrapper.WrapperType
import akakyi.bot.tasksReminder.reminder.service.ReminderService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main(args: Array<String>) {
    val wrapper = MessageApiWrapperRegistry.getWrapper(WrapperType.KOTLIN_WRAPPER)

    val sequence = wrapper
            .sequence("/start") { context ->
                ReminderService.processOnStartChatting(context)
            }
    wrapper.completeSequence(sequence)

    wrapper.startService()
}