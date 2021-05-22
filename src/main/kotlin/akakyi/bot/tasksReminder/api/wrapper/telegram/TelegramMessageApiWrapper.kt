package akakyi.bot.tasksReminder.api.wrapper.telegram

import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapper
import com.elbekD.bot.Bot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

object TelegramMessageApiWrapper : MessageApiWrapper {

    private val TOKEN = "1814732786:AAGBKNV8Kih3Y_zQznitu69uO6pVNq9V8dA"

    private val BOT_NAME = "TyZabylZadachuBot"

    private val BOT = Bot.createPolling(BOT_NAME, TOKEN)

    override suspend fun onMessageForCurrentChat(message: String, callbackMessageProducer: suspend () -> String) {
        BOT.onCommand(message) { msg, _ ->
            val callbackMessage = callbackMessageProducer.invoke()
            BOT.sendMessage(msg.chat.id, callbackMessage)
        }
    }

    override suspend fun sendMessageToAllChats(message: String) {
        val allChatsJob = GlobalScope.async {
            getAllChatIds()
        }
        allChatsJob.await()
                .forEach {
                    BOT.sendMessage(it, message)
                }
    }

    override suspend fun startService() {
        BOT.start()
    }

    override suspend fun stopService() {
        BOT.stop()
    }

    private suspend fun getAllChatIds() = listOf("1", "2")
}