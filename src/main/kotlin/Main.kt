import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main() {
    val bot = bot {
        token = "7721734270:AAGLddVTgVR4Htp3KmNjbJRfE6ehL5GBM9o"
        dispatch {
            text {
                println(message.chat.id)
                println(text)

                bot.sendMessage(ChatId.fromId(message.chat.id), text = text)
                message
            }
        }
    }
    bot.startPolling()
}