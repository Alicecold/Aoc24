import java.io.File
import kotlin.math.abs

class Day02 {
    fun run(){
        val input1 = File("src/main/resources/actual/day02.txt")
        val test = File("src/main/resources/test/day2test.txt")
        println(part1(input1))
        println(part2(input1))
    }

    private fun part1(input :File): Int {
        val lines = input.useLines { it.toList() }

        val values = lines.map { it.split(" ").map { i -> i.toInt() } }

        return values.count { it.isSafe() }
    }

    private fun List<Int>.isSafe() : Boolean{
        return if (this != this.sorted() && this != this.sorted().asReversed()){
            false
        } else {
            this.mapIndexed { index, it -> if (index != 0) abs(it - this[index - 1]) else null }
                .filterNotNull()
                .all { it in 1..3 }
        }
    }

    private fun part2(input :File): Int {
        val lines = input.useLines { it.toList() }

        val values = lines.map { it.split(" ").map { i -> i.toInt() } }

        return values.count { it.isSafe() } +
                values.filterNot { it.isSafe() }
                    .count {it.withSingleFailingRemoved() }
    }

    private fun List<Int>.withSingleFailingRemoved(): Boolean {
        return List(this.size) { index ->
            (this.subList(0, index) + this.subList(index + 1, this.size)).isSafe()
        }.any { it }
    }
}