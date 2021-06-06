package akakyi.bot.tasksReminder.api.wrapper.telegram

import akakyi.bot.tasksReminder.api.wrapper.ChatContext
import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapper
import com.elbekD.bot.Bot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

internal object TelegramMessageApiWrapper : MessageApiWrapper {

    private val TOKEN = "1814732786:AAGBKNV8Kih3Y_zQznitu69uO6pVNq9V8dA"

    private val BOT_NAME = "TyZabylZadachuBot"

    private val BOT = Bot.createPolling(BOT_NAME, TOKEN)

    override suspend fun onMessageForAnyChat(
            message: String,
            messageProducer: suspend (context: ChatContext) -> Unit
    ) {
        BOT.onCommand(message) { msg, _ ->
            val chatId = msg.chat.id
            val context = ChatContext(chatId.toString())
            messageProducer.invoke(context)
        }
    }

    override suspend fun onMessageForAnyChatWithResponse(
            message: String,
            messageProducer: suspend (context: ChatContext) -> String
    ) {
        BOT.onCommand(message) { msg, _ ->
            val chatId = msg.chat.id
            val context = ChatContext(chatId.toString())
            val callbackMessage = messageProducer.invoke(context)
            BOT.sendMessage(chatId, callbackMessage)
        }
    }

//    override suspend fun sendMessageToAllChats(message: String) {
//        val allChatsJob = GlobalScope.async {
//            getAllChatIds()
//        }
//        allChatsJob.await()
//                .forEach {
//                    BOT.sendMessage(it, message)
//                }
//    }

    override suspend fun startService() {
        BOT.start()
    }

    override suspend fun stopService() {
        BOT.stop()
    }

}