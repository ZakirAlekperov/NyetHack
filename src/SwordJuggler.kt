
fun main() {
    var swordJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if(isJugglingProficient){
        swordJuggling = 2
    }
    try {
        profiencyCheck(swordJuggling)
        swordJuggling = swordJuggling!!.plus(1)
        juggleSwords(swordJuggling)
    }catch (e: Exception){
        println(e)
    }

    println("You juggle $swordJuggling swords!")

}

fun profiencyCheck(swordJuggling: Int?){
    checkNotNull(swordJuggling, {"Player cannot juggle swords"})
}

fun juggleSwords(swordJuggling: Int){
    require(swordJuggling >=3, {"Juggle at least 3 swords to be exciting"})
}

class UnskilledSwordJuggleException():
        IllegalStateException("Player cannot juggle swords")