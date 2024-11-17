import com.github.kotlintelegrambot.dispatcher.handlers.TextHandlerEnvironment

class AphirriLogger {
    companion object {
        fun TextHandlerEnvironment.log() {
            println("Message:" + this.text)
            println("Chat id:" + this.message.chat.id)
            println("From:" + this.message.from?.id)
        }
    }
}