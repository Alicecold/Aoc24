import java.io.File

class Day07 {

    fun run() {
        val input = File("src/main/resources/actual/day07.txt")
        val test = File("src/main/resources/test/day07test.txt")
        println(part1(input))
        println(part2(input))
    }

    private fun part1(file: File): ULong {
        val calculations = file.useLines { it.toList() }.map {
            val calculation = it.split(": ")
            Pair(calculation[0].toULong(), calculation[1].split(" ").map { i -> i.toULong() })
        }

        return calculations.filter { it.second.possibleCaluclation(it.first) }.sumOf { it.first }

    }

    private fun List<ULong>.possibleCaluclation(requiredResult: ULong): Boolean {

        return if (this.sum() == requiredResult || this.reduce { acc, it -> acc * it } == requiredResult) {
            true
        } else {
            (this.size).possibleOperators()
                .any { this.reduceIndexed { index, acc, i -> if (it[index] == '+') acc + i else acc * i } == requiredResult }
        }
    }

    private fun Int.possibleOperators(): List<String> {
        val operators = listOf("+", "*")
        val result = mutableListOf<String>()

        fun generate(current: String, length: Int) {
            if (length == 0) {
                result.add(current)
                return
            }
            for (operator in operators) {
                generate(current + operator, length - 1)
            }
        }

        generate("", this)
        return result
    }

    private fun part2(file: File): ULong {
        val calculations = file.useLines { it.toList() }.map {
            val calculation = it.split(": ")
            Pair(calculation[0].toULong(), calculation[1].split(" ").map { i -> i.toULong() })
        }

        return calculations.filter { it.second.possibleCaluclation2(it.first) }.sumOf { it.first }
    }

    private fun List<ULong>.possibleCaluclation2(requiredResult: ULong): Boolean {

        return if (this.sum() == requiredResult || this.reduce { acc, it -> acc * it } == requiredResult) {
            true
        } else {
            (this.size).possibleOperators2()
                .any { this.reduceIndexed { index, acc, i -> if (it[index] == '+') acc + i else if(it[index] == '*') acc * i  else (acc.toString() + i.toString()).toULong()} == requiredResult }
        }
    }

    private fun Int.possibleOperators2(): List<String> {
        val operators = listOf("+", "*", "x")
        val result = mutableListOf<String>()

        fun generate(current: String, length: Int) {
            if (length == 0) {
                result.add(current)
                return
            }
            for (operator in operators) {
                generate(current + operator, length - 1)
            }
        }

        generate("", this)
        return result
    }
}




