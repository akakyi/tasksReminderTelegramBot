@file:JvmName("Main")

package akakyi.bot.tasksReminder

import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapperFactory
import akakyi.bot.tasksReminder.api.wrapper.WrapperType

suspend fun main(args: Array<String>) {
    val wrapper = MessageApiWrapperFactory.getWrapper(WrapperType.KOTLIN_WRAPPER)
    wrapper.onMessageForCurrentChat("/start") {
        "Hello World!"
    }
    wrapper.startService()
}