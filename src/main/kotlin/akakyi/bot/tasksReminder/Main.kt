@file:JvmName("Main")

package akakyi.bot.tasksReminder

import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapperRegistry
import akakyi.bot.tasksReminder.api.wrapper.WrapperType

suspend fun main(args: Array<String>) {
    val wrapper = MessageApiWrapperRegistry.getWrapper(WrapperType.KOTLIN_WRAPPER)
    wrapper.onMessageForAnyChatWithResponse("/start") { context ->
        context.message ?: "message is null!!!"
    }
    wrapper.startService()
}