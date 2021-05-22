package akakyi.bot.tasksReminder.api.wrapper

import akakyi.bot.tasksReminder.api.wrapper.telegram.TelegramMessageApiWrapper

object MessageApiWrapperFactory {

        fun getWrapper(type: WrapperType): MessageApiWrapper {
            return when (type) {
                WrapperType.KOTLIN_WRAPPER -> TelegramMessageApiWrapper
            }
        }

}