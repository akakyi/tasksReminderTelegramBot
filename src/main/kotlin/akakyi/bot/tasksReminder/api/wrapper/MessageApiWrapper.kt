package akakyi.bot.tasksReminder.api.wrapper

interface MessageApiWrapper {

    suspend fun onMessageForAnyChat(message: String, messageProducer: suspend (context: MessageContext) -> Unit)

    suspend fun onMessageForAnyChatWithResponse(
            message: String,
            messageProducer: suspend (context: MessageContext) -> String
    )

    suspend fun sequence(
            triggerMessage: String,
            startFunction: (context: MessageContext) -> Unit
    ): MessageSequence

    suspend fun completeSequence(sequence: MessageSequence)

    suspend fun startService()

    suspend fun stopService()

}