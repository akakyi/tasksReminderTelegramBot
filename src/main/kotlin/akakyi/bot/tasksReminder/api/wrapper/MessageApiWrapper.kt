package akakyi.bot.tasksReminder.api.wrapper

interface MessageApiWrapper {

    suspend fun sendMessage(chatId: String, message: String)

    suspend fun onMessageForAnyChat(message: String, messageProducer: suspend (context: MessageContext) -> Unit)

    suspend fun onMessageForAnyChatWithResponse(
            message: String,
            messageProducer: suspend (context: MessageContext) -> String
    )

    fun sequence(
            triggerMessage: String,
            startFunction: (context: MessageContext) -> Unit
    ): MessageSequence

    suspend fun completeSequence(messageSequence: MessageSequence)

    suspend fun startService()

    suspend fun stopService()

}