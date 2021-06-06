package akakyi.bot.tasksReminder.api.wrapper

interface MessageApiWrapper {

    suspend fun onMessageForAnyChat(message: String, messageProducer: suspend (context: ChatContext) -> Unit)

    suspend fun onMessageForAnyChatWithResponse(
            message: String,
            messageProducer: suspend (context: ChatContext) -> String
    )

    suspend fun startService()

    suspend fun stopService()

}