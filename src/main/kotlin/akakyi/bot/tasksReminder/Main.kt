@file:JvmName("Main")

package akakyi.bot.tasksReminder

import com.elbekD.bot.Bot

private val TOKEN = "1814732786:AAGBKNV8Kih3Y_zQznitu69uO6pVNq9V8dA"

private val BOT_NAME = "TyZabylZadachuBot"

fun main(args: Array<String>) {
    val bot = Bot.createPolling(BOT_NAME, TOKEN)
    bot.onCommand("/start") { msg, _ ->
        bot.sendMessage(msg.chat.id, "Hello World!")
    }
    bot.start()
}