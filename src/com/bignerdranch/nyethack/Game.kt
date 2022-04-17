package com.bignerdranch.nyethack

import java.util.*
import kotlin.system.exitProcess

fun main() {
    Game.play()
}

object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room"))
    )

    init {
        println("Welcome, adventurer.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            //Игровой процесс
            println(currentRoom.description())
            println(currentRoom.load())

            //Состояние игрока
            printPlayerStatus(player)
            println("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()} " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"}"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.uppercase(Locale.getDefault()))
            val newPosition = direction.updateCoordinate(player.currentPossition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction is out of bounds")
            } else {
            }

            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPossition = newPosition
            currentRoom = newRoom
            "Ok, you move $direction to the ${newRoom.name}. " +
                    "\n${newRoom.load()}"
        } catch (e: Exception) {
            "Invalid direction: $directionInput"
        }

    private fun quit() {
        println(">>>> EXIT<<<<")
        exitProcess(0)
    }

    private fun map(): String {
        var str: String
        var worldMapASCII = mutableListOf(
            mutableListOf("0", "0", "0"),
            mutableListOf("0", "0")
        )
        worldMap.forEach() { level: List<Room> ->
            level.forEach() {
                if (it.name == currentRoom.name) {
                    worldMapASCII[worldMap.indexOf(level)][level.indexOf(it)] = "X"
                    println("замена")
                } else {
                    println("нет замены")
                }
            }
        }
        str = worldMapASCII[0].joinToString(" ") + "\n" +
                worldMapASCII[1].joinToString(" ")
        return str
    }

    private fun ring() =
        if (currentRoom.name == "Town Square") {
            TownSquare().ringBell()
        } else {
            "Imposible"
        }

    private fun fight() = currentRoom.monster?.let {
        while (player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete."
    } ?: "There nothing here to fight"

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${ player.attack(monster)} damage!")
        println("${player.name} ${player.healthPoints}")
        println("${monster.name} ${monster.healthPoints}")
        if (player.healthPoints <=0 ){
            println(">>>> You have been defeated! Thanks for playing.<<<<")
            exitProcess(0)
        }
        if (monster.healthPoints <= 0){
            println(">>>>${monster.name} has been defeated! <<<<")
            currentRoom.monster = null
        }
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.lowercase(Locale.getDefault())) {
            "move" -> move(argument)
            "quit" -> quit()
            "map" -> map()
            "ring" -> ring()
            "fight" -> fight()
            else -> commandNotFound()
        }

        private fun commandNotFound() = "I'm not quite sure " +
                "what you're trying to do!"
    }
}



