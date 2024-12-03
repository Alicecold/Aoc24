import java.io.File
import kotlin.math.abs

class Day01 {

    fun run(){
        val input1 = File("src/main/resources/actual/day1part1.txt")
        val test = File("src/main/resources/test/day01test.txt")
        println(part1(test))
        println(part2(test))
    }

    fun part1(input : File): Int {
        val list1 :MutableList<Int> = mutableListOf()
        val list2 :MutableList<Int> = mutableListOf()
        input.useLines { it.toList() }.forEach{
            val map = it.split("   ")
            list1.add(map[0].toInt())
            list2.add(map[1].toInt())
        }

        list1.sort()
        list2.sort()

        val sortedIds = list1.zip(list2)

        return sortedIds.sumOf { abs(it.first - it.second) }

    }


    fun part2(input : File): Int {
        val list1 :MutableList<Int> = mutableListOf()
        val list2 :MutableList<Int> = mutableListOf()
        input.useLines { it.toList() }.forEach{
            val map = it.split("   ")
            list1.add(map[0].toInt())
            list2.add(map[1].toInt())
        }

        val similarities = list1.map { it * list2.count{x -> it == x} }
        return similarities.sum()

    }

}