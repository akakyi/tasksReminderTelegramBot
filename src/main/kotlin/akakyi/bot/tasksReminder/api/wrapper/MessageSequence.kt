package akakyi.bot.tasksReminder.api.wrapper

class MessageSequence internal constructor(
        internal val triggerMessage: String,
        internal val startFunction: (context: MessageContext) -> Unit
) {

    private val producers = mutableListOf<(context: MessageContext) -> String>()

    internal val sequence: Sequence<(context: MessageContext) -> String>
    get() = this.producers.asSequence()

    fun then(messageProducer: (context: MessageContext) -> String): MessageSequence {
        this.producers.add(messageProducer)
        return this
    }

}