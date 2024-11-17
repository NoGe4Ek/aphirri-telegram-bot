import AphirriLogger.Companion.log
import EmojiTextHelper.toEmojiText
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.Message
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.types.TelegramBotResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.offsetAt

object AphirriBot {
    fun init() {
        val multipleThreadsContext = newFixedThreadPoolContext(4, "Threads")
        val scope = CoroutineScope(multipleThreadsContext)

        val CHAT_ID = -1002278988408

        val bot = bot {

            token = "7721734270:AAGLddVTgVR4Htp3KmNjbJRfE6ehL5GBM9o"
            dispatch {
                command("start") {
                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Hi there!")
                    result.fold({
                        // do something here with the response
                    }, {
                        // do something with the error
                    })
                }
                text {
                    if (message.from?.id != 357609663L) return@text
                    log()

                    val instant = Instant.fromEpochSeconds(message.date)
                    val format =
                        DateTimeComponents.Format { dayOfMonth(); char(' '); monthName(MonthNames.ENGLISH_ABBREVIATED); }
                    val date = instant.format(format, TimeZone.currentSystemDefault().offsetAt(instant))

                    var pollMessage: TelegramBotResult<Message>? = null
                    if (text == "/today") {
                        pollMessage = bot.sendPoll(
                            chatId = ChatId.fromId(CHAT_ID),
                            options = listOf("18:00", "19:00", "20:00"),
                            question = "Сегодня?",
                            disableNotification = true,
                            replyToMessageId = message.messageId,
                            allowSendingWithoutReply = false
                        )
                        bot.deleteMessage(chatId = ChatId.fromId(CHAT_ID), messageId = message.messageId)

                        scope.launch {
                            delay(5000L)
                            val poll = bot.stopPoll(
                                chatId = ChatId.fromId(CHAT_ID),
                                messageId = pollMessage?.get()?.messageId ?: 0
                            )
                            val result = poll.get().options.maxBy { it.voterCount }.text
                            val resultMessage =
                                bot.sendMessage(
                                    chatId = ChatId.fromId(CHAT_ID),
                                    parseMode = ParseMode.MARKDOWN_V2,
                                    text = "${result.toEmojiText()} \\| $date",
                                    messageThreadId = 917
                                )
                            bot.pinChatMessage(
                                chatId = ChatId.fromId(resultMessage.get().chat.id),
                                messageId = resultMessage.get().messageId,
                                disableNotification = true
                            )
                        }
                    }

                    // check regex /today
                    // create poll
                    // get results
                    // analyse, send analytic result
                    // pin
                    // send notification 10 minutes before start
                    //
                    // sync with meetings on mobile app
                    // send notification for members before start

                    // bot.editMessageText(ChatId.fromId(-1002312275840), 197, text = "edited1")
                    // опрос bot.sendPoll()
                    // bot.stopPoll().get().options.first().voterCount

//                if (text[0] == '/')
//                    bot.sendMessage(ChatId.fromId(message.chat.id), text = "Это команда")
//                else {
//                    println(message.from?.username)
//                    bot.sendMessage(ChatId.fromId(message.chat.id), text = text)
//
//                }
                }
            }
        }

        bot.startPolling()
    }
}