import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main() {
    val clb = BaseInfo()
    Runtime.getRuntime().availableProcessors()
    val bot = bot {

        token = "7721734270:AAGLddVTgVR4Htp3KmNjbJRfE6ehL5GBM9o"
        dispatch {
            text {
                clb.checkAndAdd(message.from?.id.toString(), message.from?.username.toString())
                if (checkComm(text) == true) {
                    // todo заменить эту проверку на какую-нибудь системную у бота
                    clb.ScoreAdd(message.from?.id.toString(), text)

                }
            }
            command("rate") {
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = clb.RatingSet(message.from?.id.toString()))
            }
        }
    }
    bot.startPolling()
}