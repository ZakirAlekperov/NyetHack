const val TAVERN_NAME = "Taernyl's Folly"

fun main() {
    placeOrder("shandy,Dragon's Breath,5.91")
 //   placeOrder("elixir, Shirley's Temple, 4.12")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[AEIUaeiou]")){
        when(it.value){
            "a" -> "4"
            "A" -> "4"
            "e" -> "3"
            "E" -> "3"
            "i" -> "1"
            "I" -> "1"
            "u" -> "|_|"
            "U" -> "|-|"
            else -> it.value
        }
    }

private fun placeOrder(menuData: String){
    val indexofApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexofApostrophe)
    println("Madrigal speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(',')
    val message = "Madrigal buys a $name ($type) for $price"
    println(message)

    val phrase = if (name == "Dragon's Breath"){
        "Madrigal exclaims: ${toDragonSpeak("IT'S GOT WHAT")}"
    }else{
        "Madrigal says: Thanks for the $name"
    }
    println(phrase)

}



