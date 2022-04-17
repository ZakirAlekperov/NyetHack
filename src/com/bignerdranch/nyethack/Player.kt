package com.bignerdranch.nyethack

import com.bignerdranch.nyethack.extensions.random as randomizer
import java.io.File
import java.util.*

class Player(_name: String,
             override var healthPoints: Int = 100,
             var isBlessed: Boolean = false,
             private  var isImmortal: Boolean
) : Fightable {
    var name: String = _name
        private set(value) {
            field = value.trim()
        }
        get() {
            return "$field of $hometown"
        }
    val hometown by lazy { selectHometown() }
    var currentPossition = Coordinate(0, 0)

    init {
        require(healthPoints > 0, { "healthPoints must be greater than zero" })
        require(name.isNotBlank(), { "Player must have a name" })
    }

    constructor(name: String) : this(
        name,
        isBlessed = true,
        isImmortal = false
    ) {
        if (name.lowercase(Locale.getDefault()) == "kar") healthPoints = 40
    }


    fun castFireball(numFierball: Int = 2) =
        println("A glass of fireball springs into existence. Delicious! (x$numFierball)")

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus(): String =
        when (healthPoints) {
            100 -> "is in excellent condition!"
            in 90..99 -> "has a few scrathes."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                "has some minor wounds."
            }
            in 15..74 -> "looks pretty hurt."
            else -> "is in awful condition!"
        }

    private fun selectHometown()=File("data/towns.txt")
        .readText().split(" ").randomizer()


    override val diceCount: Int = 3
    override val diceSide: Int = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}

