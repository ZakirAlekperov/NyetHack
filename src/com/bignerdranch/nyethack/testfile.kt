package com.bignerdranch.nyethack

import java.io.File
import java.util.*

fun main() {

    val player = Player1("Madrigal", 100, true, false)
    println(player.hometown)
    println("${player.name} ")

}

class Player1(_name: String,
             var healthPoints: Int = 100,
             val isBlessed: Boolean,
             private val isImmortal: Boolean) {
    val hometown = selectHometown()

    var name: String = "$_name of $hometown"

    private fun selectHometown(): String {
        var text = File("data/towns.txt")
            .readText()
            .split("\n")
        return text[8]
    }
}
//    private fun selectHometown() =
//}

