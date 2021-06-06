package akakyi.bot.tasksReminder.api.wrapper.telegram

import akakyi.bot.tasksReminder.api.wrapper.MessageContext
import akakyi.bot.tasksReminder.api.wrapper.MessageApiWrapper
import akakyi.bot.tasksReminder.api.wrapper.MessageSequence
import com.elbekD.bot.Bot
import com.elbekD.bot.feature.chain.chain

internal object TelegramMessageApiWrapper : MessageApiWrapper {

    private val TOKEN = "1814732786:AAGBKNV8Kih3Y_zQznitu69uO6pVNq9V8dA"

    private val BOT_NAME = "TyZabylZadachuBot"

    private val BOT = Bot.createPolling(BOT_NAME, TOKEN)

    override suspend fun onMessageForAnyChat(
            message: String,
            messageProducer: suspend (context: MessageContext) -> Unit
    ) {
        BOT.onCommand(message) { msg, _ ->
            val chatId = msg.chat.id
            val context = MessageContext(chatId.toString(), msg.text)
            messageProducer(context)
        }
    }

    override suspend fun onMessageForAnyChatWithResponse(
            message: String,
            messageProducer: suspend (context: MessageContext) -> String
    ) {
        BOT.onCommand(message) { msg, _ ->
            val chatId = msg.chat.id
            val context = MessageContext(chatId.toString(), msg.text)
            val callbackMessage = messageProducer(context)
            BOT.sendMessage(chatId, callbackMessage)
        }
    }

    override suspend fun sequence(
            triggerMessage: String,
            startFunction: (context: MessageContext) -> Unit
    ) = MessageSequence(triggerMessage, startFunction)

    override suspend fun completeSequence(sequence: MessageSequence) {
        val chain = BOT.chain(sequence.triggerMessage) { msg ->
            val chatId = msg.chat.id
            val messageContext = MessageContext(chatId.toString(), msg.text)
            sequence.startFunction(messageContext)
        }

        sequence.sequence.forEach { action ->
            chain.then { msg ->
                val chatId = msg.chat.id
                val messageContext = MessageContext(chatId.toString(), msg.text)
                action(messageContext)
            }
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