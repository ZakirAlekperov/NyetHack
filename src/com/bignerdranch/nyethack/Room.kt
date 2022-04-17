package com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open val dangerlevel = 5
    var monster: Monster? = Goblin()

    fun description() = "Room: $name\n" +
            "Danger level: $dangerlevel \n" +
            "Creature: ${monster?.description ?: "none"}"

    open fun load() = "Nothing much to see here"
}

class TownSquare : Room("Town Square") {
    override val dangerlevel: Int
        get() = super.dangerlevel - 3
    private var bellSound = "GWONG"
    final override fun load() =
        "The village rally and cheer as you enter!" +
                "\n${ringBell()}"

    fun ringBell() = "The bell tower announcement yor arrival. $bellSound"
}