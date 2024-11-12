import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main() {
    Runtime.getRuntime().availableProcessors()
    val bot = bot {

        token = "7721734270:AAGLddVTgVR4Htp3KmNjbJRfE6ehL5GBM9o"
        dispatch {
            text {

                // todo заменить эту проверку на какую-нибудь системную у бота
                if (message.chat.id == -1002312275840) {
                    println(message.from?.username)
                    bot.sendMessage(ChatId.fromId(message.chat.id), text = text)
                }
            }
        }
    }

    bot.startPolling()
}