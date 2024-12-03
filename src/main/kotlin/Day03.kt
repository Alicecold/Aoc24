import java.io.File

class Day03 {
    fun run() {
        val input1 = File("src/main/resources/actual/day03.txt")
        val test = File("src/main/resources/test/day03test.txt")
        val test2 = File("src/main/resources/test/day03test02.txt")
        println(part1(test))
        println(part2(test2))
    }

    private fun part1(input: File): Int {
        val lines = input.useLines { it.toList() }

        val regex = Regex("(mul\\(\\d{1,3},\\d{1,3}\\))")

        val values = lines.map { regex.findAll(it).toList().map { x -> x.value } }.flatten()

        return values.sumOf { it.multiply() }
    }

    private fun String.multiply(): Int {
        val toMultiply = this.split("mul(", ",", ")").filterNot { it.isEmpty() }
        return toMultiply[0].toInt() * toMultiply[1].toInt()
    }

    private fun part2(input: File): Int {
        val lines = input.useLines { it.toList() }

        val regex = Regex("(mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don\\'t\\(\\))")

        val values = lines.map { regex.findAll(it).toList().map { x -> x.value } }.flatten()

        return values.filterDo().sumOf { it.multiply() }
    }

    private fun List<String>.filterDo(): List<String> {
        var on = true

        return this.mapNotNull {
            if (it == "don't()") {
                on = false
                null
            } else if (it == "do()") {
                on = true
                null
            } else if (on) {
                it
            } else {
                null
            }
        }

    }
}