//package dev.aphirri
//
//object EmojiTextHelper {
//    fun String.toEmojiText() = this.map { Emoji.getByChar(it).toEmojiText() }.joinToString(separator = "")
//
//
//    enum class Emoji(val char: Char, val title: String, val id: Long) {
//        NUM_1('1', "1\uFE0F⃣", 5269520668524821648),
//        NUM_2('2', "2\uFE0F⃣", 5269293426100157176),
//        NUM_3('3', "3\uFE0F⃣", 5269412160471053597),
//        NUM_4('4', "4\uFE0F⃣", 5269624830071686015),
//        NUM_5('5', "5\uFE0F⃣", 5271922568855565125),
//        NUM_6('6', "6\uFE0F⃣", 5280603870286853861),
//        NUM_7('7', "7\uFE0F⃣", 5269506774305618382),
//        NUM_8('8', "8\uFE0F⃣", 5282750860013614452),
//        NUM_9('9', "9\uFE0F⃣", 5282795995824929597),
//        NUM_0('0', "0\uFE0F⃣", 5285002281870177414),
//        SYM_COLON(':', "\uD83D\uDD23", 5233546443360318055);
//
//        companion object {
//            fun getByChar(char: Char) = entries.first { it.char == char }
//            fun Emoji.toEmojiText() = "![${this.title}](tg://emoji?id=${this.id})"
//        }
//    }
//}