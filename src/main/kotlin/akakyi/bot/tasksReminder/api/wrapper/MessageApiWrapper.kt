package akakyi.bot.tasksReminder.api.wrapper

interface MessageApiWrapper {

    suspend fun onMessageForCurrentChat(message: String, messageProducer: suspend () -> String)

    suspend fun sendMessageToAllChats(message: String)

    suspend fun startService()

    suspend fun stopService()

}