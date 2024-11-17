package dev.aphirri

import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

fun main() {
    val db = DataBase()
    val bot = bot {

        token = "7721734270:AAGLddVTgVR4Htp3KmNjbJRfE6ehL5GBM9o"
        dispatch {
            text {
                val from = message.from ?: return@text
                val username = from.username.orEmpty()

                db.addNewUserIfNotExist(from.id, username)
                if (checkNotCommand(text)) {
                    db.addScore(from.id, text)
                }
            }
            command("rate") {
                val from = message.from ?: return@command
                bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = db.ratingSet(from.id))
            }
        }
    }
    bot.startPolling()
}