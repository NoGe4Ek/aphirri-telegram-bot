
class BaseInfo {
    private val mu = mutableMapOf<String, Int>() //связывает id и score
    private val Players = mutableMapOf<String, String>() //связывает id и name

    fun checkAndAdd(id: String, name: String): Boolean {
        val c = mu.containsKey(id)


        if (mu.containsKey(id) != true) {
            mu.put(id, 0)
            Players.put(id, name)
            println("Не обнаружен, добавлен")
        }

        return c
    }

    fun ScoreAdd(id: String, text: String) {
        val words = text.split(" ").toTypedArray()
        var scr = 0
        for(i in 0..(words.size-1)) {
            if ((6 > words[i].count()) and (2 < words[i].count())) {
                scr += 10
            } else if (words[i].count() > 5) {
                scr += 15
            }
        }
        if ((',' in text) or (';' in text)){
            scr += 20
        }
        scr += mu.get(id)!!.toInt()
        mu.set(id, scr)

        return
    }

    fun RatingSet(id: String): String {
        val score = mu.get(key = id)
        var i1 = 0
        var text: String
        for(i in 0..RatingNames.Size.min) {
            if (score!! >= RatingNames.entries[i].min) {
                i1 = i
                break
            }
        }
        if (i1 == 0) {
            text = "@${Players.get(id)} $score PHI, ${RatingNames.entries[i1].name} (от ${RatingNames.entries[i1].min} PHI)"
        } else {
            text = "@${Players.get(id)} $score PHI, ${RatingNames.entries[i1].name} (от ${RatingNames.entries[i1].min} PHI до ${RatingNames.entries[i1 - 1].min} PHI)"
        }
        return text
    }

}
fun checkComm(text: String): Boolean {
    var a: Boolean
    if (text[0] == '/') {
        a = false

    } else {
        a = true
    }
    return a
}
