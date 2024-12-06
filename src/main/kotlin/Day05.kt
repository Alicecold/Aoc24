import java.io.File
import kotlin.math.ceil
import kotlin.math.floor

class Day05 {
    fun run() {
        val input = File("src/main/resources/actual/day05.txt")
        val test = File("src/main/resources/test/day05test.txt")
        println(part1(test))
        println(part2(test))
    }

    private fun part1(file: File): UInt {
        val rules = mutableListOf<Pair<UInt, UInt>>()
        val instructions = mutableListOf<List<UInt>>()
        var currentlyRules = true
        file.useLines { it.toList() }
            .forEach {
            if(it.isEmpty())  {
                currentlyRules = false
            } else if (currentlyRules){
                val (key, value) = it.split("|").map {i -> i.toUInt() }
                rules.add(key to value)
            } else {
                instructions.add(it.split(",").map { i -> i.toUInt() })
            }
        }
        val x = instructions.filter { it.isValidBy(rules) }
        return x.sumOf { it.middleValue() }
    }

    private fun List<UInt>.isValidBy(rules: List<Pair<UInt, UInt>>): Boolean {
        return rules.filter { (key, value) -> key in this && value in this }.all { (key, value) -> this.indexOf(key) < this.indexOf(value) }
    }

    private fun List<UInt>.middleValue(): UInt {
        return this[this.size / 2]
    }

    private fun part2(file: File): UInt {
        val rules = mutableListOf<Pair<UInt, UInt>>()
        val instructions = mutableListOf<List<UInt>>()
        var currentlyRules = true
        file.useLines { it.toList() }
            .forEach {
                if(it.isEmpty())  {
                    currentlyRules = false
                } else if (currentlyRules){
                    val (key, value) = it.split("|").map {i -> i.toUInt() }
                    rules.add(key to value)
                } else {
                    instructions.add(it.split(",").map { i -> i.toUInt() })
                }
            }

        val comparator = Comparator<UInt> { first, second -> if (rules.any {(key, value) -> first == key && second == value }) 0 else if (rules.any {(key, value) -> second == key && first == value }) -1 else 0  }
        val x = instructions.filterNot { it.isValidBy(rules) }.map { it.sortedWith(comparator) }
        return x.sumOf { it.middleValue() }
    }


}




