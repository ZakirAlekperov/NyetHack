package com.bignerdranch.nyethack

import kotlin.random.Random


interface Fightable {
    var healthPoints: Int
    val diceCount: Int
    val diceSide: Int
    val damageRoll: Int
        get() = (0 until diceCount).map {
            Random.nextInt(diceSide + 1)
        }.sum()

    fun attack(opponent: Fightable): Int
}

abstract class Monster(
    val name: String,
    val description: String,
    override var healthPoints: Int
) : Fightable {
    override fun attack(opponent: Fightable): Int {
        val damageDelt = damageRoll
        opponent.healthPoints -= damageDelt
        return damageDelt
    }
}

class Goblin(
    name: String = "Goblin",
    description: String = "A nasty-looking goblin",
    override var healthPoints: Int = 30
) : Monster(name, description, healthPoints) {
    override val diceCount = 2
    override val diceSide = 8
}
