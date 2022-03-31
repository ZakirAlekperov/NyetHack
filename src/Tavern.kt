import java.util.*

fun main() {
    var beverage = readLine()?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
//    beverage = null

    println(beverage)
}


