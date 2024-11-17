package dev.aphirri

data class User(val id: Long, val name: String, var score: Long) {
    override fun equals(other: Any?): Boolean {
        val user = other as User
        return user.id == id
    }

    override fun hashCode(): Int {
        return id.toInt()
    }
}

class DataBase {
    private val users: MutableSet<User> = mutableSetOf() // связывает id и user

    fun addScore(id: Long, text: String) {
        val user = getUserById(id) ?: return
        user.score += getScoreFromText(text)
    }

    fun addNewUserIfNotExist(id: Long, name: String) {
        if (getUserById(id) == null) {
            val newUser = User(id, name, 0)
            users.add(newUser)
            println("Новый пользователь: $name")
        }
    }

    private fun getUserById(id: Long) = users.firstOrNull { it.id == id }

    private fun getScoreFromText(text: String): Long {
        val words = text.split(" ")
        var scr = 0L
        words.forEach { word ->
            if ((6 > word.count()) and (2 < word.count())) {
                scr += 10
            } else if (word.count() > 5) {
                scr += 15
            }
        }
        if ((',' in text) or (';' in text)) {
            scr += 20
        }
        return scr
    }

    fun ratingSet(id: Long): String {
        val user = getUserById(id) ?: return ""
        val score = user.score
        var i1 = 0
        for (i in 0..RatingNames.Size.min) {
            if (score >= RatingNames.entries[i].min) {
                i1 = i
                break
            }
        }
        val text: String = if (i1 == 0) {
            "@${user.name} $score PHI, ${RatingNames.entries[i1].name} (от ${RatingNames.entries[i1].min} PHI)"
        } else {
            "@${user.name} $score PHI, ${RatingNames.entries[i1].name} (от ${RatingNames.entries[i1].min} PHI до ${RatingNames.entries[i1 - 1].min} PHI)"
        }
        return text
    }

}

fun checkNotCommand(text: String) = text[0] != '/'
