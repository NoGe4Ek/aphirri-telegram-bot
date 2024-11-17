package dev.aphirri

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import dev.aphirri.utils.checkComm

/** todo общие замечания
 * 1. исправить все желтые ворнинги
 * 2. переделать хранение user на одну map типа User (класс я создал). В качестве id использовать Long
 * 3. переписать все имена функций в соответствии с правилами. Желательно сначала прочитать
 * https://kotlinlang.org/docs/coding-conventions.html#naming-rules
 * https://kotlinlang.org/docs/coding-conventions.html#formatting
 * 4. переписать имена переменных, чтобы они хорошо понимались (проверить, точно ли нужена та или иная переменная, нельзя ли без нее обойтись)
 * 5. переписать все for циклы на forEach (ради практики работы с таким API)
 * 6. чем больше вспомнишь из того, что мы вместе делали - тем лучше. На Set переписывать не надо
 * */
fun main() {
    val clb = DataBase()
    val bot = bot {
        token = "7721734270:AAGLddVTgVR4Htp3KmNjbJRfE6ehL5GBM9o"
        dispatch {
            text {
                clb.checkAndAdd(message.from?.id.toString(), message.from?.username.toString())
                if (text.checkComm() == true) {
                    clb.ScoreAdd(message.from?.id.toString(), text)

                }
            }
            command("rate") {
                bot.sendMessage(
                    chatId = ChatId.fromId(message.chat.id),
                    text = clb.RatingSet(message.from?.id.toString())
                )
            }
        }
    }
    bot.startPolling()
}