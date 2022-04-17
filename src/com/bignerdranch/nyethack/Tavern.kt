package com.bignerdranch.nyethack

import java.io.File


const val TAVERN_NAME = "Taernyl's Folly"

val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot","Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt")
    .readText()
    .split("\n")
val menuListFormat = mutableListOf<String>()
val typeList = mutableListOf<String>()
var patronGold = mutableMapOf<String, Double>()


fun main() {
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while(orderCount<=9){
        placeOrder(
            uniquePatrons.shuffled().first(),
                    menuList.shuffled().first())
        orderCount ++
    }

    displayPatronBalance()
//    com.bignerdranch.nyethack.bouncer()
//    println(com.bignerdranch.nyethack.getPatronGold)
//    com.bignerdranch.nyethack.displayMenu()
}

fun displayMenu(){
    println("*** Welcome to $TAVERN_NAME ***")
    menuList.forEach {
        val (type, name, price) = it.split(",")
        var baseString = ".................................."
        val lenbuf = baseString.substring(name.length-1, baseString.length-price.length-1)
        typeList.add(type)
        val menuItem = name.first().uppercaseChar() +
                name.substring(1) +
                lenbuf + price+","+type
        menuListFormat.add(menuItem)
    }
    typeList.distinct().forEach { type:String ->
        println("          ~[${type}]~")
        menuListFormat.forEach(){
            val (item,t) = it.split(",")
            if(t == type){
                println(item)
            }
        }

    }
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

private fun placeOrder(patronName: String, menuData: String){
    val indexofApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexofApostrophe)
    println("$patronName speaks with $tavernMaster about their order")

    val (type, name, price) = menuData.split(',')
    val message = "$patronName buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble(), patronName)

    val phrase = if (name == "Dragon's Breath"){
        "$patronName exclaims: ${toDragonSpeak("Ah, delicious $name")}"
    }else{
        "$patronName says: Thanks for the $name"
    }
    println(phrase)
}

private fun performPurchase(price: Double, patronName: String){
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
}

private fun displayPatronBalance(){
    patronGold.forEach{ (patron, balance) ->
        println("$patron, balance: ${"%.2f".format(balance)}")

    }
}

private fun bouncer(){
    patronGold.forEach{ (patron, balance) ->
        if(balance <= 0)
//            com.bignerdranch.nyethack.getPatronGold -= patron
            patronGold = (patronGold - patron) as MutableMap<String, Double>
            println(patron)
    }
}





