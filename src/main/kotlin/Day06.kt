import java.io.File

class Day06 {
    fun run() {
        val input = File("src/main/resources/actual/day06.txt")
        val test = File("src/main/resources/test/day06test.txt")
        println(part1(input)) // 4579 is too low
        //println(part2(test))
    }

    private fun part1(file: File): Int {
        val map = file.useLines { it.toList() }.map { it.toCharArray() }

        // Start Positions of Guard
        var y = map.indexOf(map.first { it.contains('^') })
        var x = map[y].indexOf('^')

        val footprints = mutableSetOf<Pair<Int, Int>>()
        footprints.add(Pair(x, y))
        var direction = 'N'

        while (map.checkForBoundary(x,y, direction)) {
            if (map.checkForObstacles(x, y, direction)) {
                when (direction) {
                    'N' -> y--
                    'E' -> x++
                    'S' -> y++
                    'W' -> x--
                }
                footprints.add(Pair(x, y))
            } else {
                direction = direction.change()
            }
        }

        return footprints.size

    }

    private fun List<CharArray>.checkForObstacles(x: Int, y: Int, direction: Char): Boolean {
        return when (direction) {
            'N' -> this[y - 1][x] != '#'
            'E' -> this[y][x + 1] != '#'
            'S' -> this[y + 1][x] != '#'
            'W' -> this[y][x - 1] != '#'
            else -> true
        }
    }

    private fun List<CharArray>.checkForBoundary(x: Int, y: Int, direction: Char): Boolean {
        return when (direction) {
            'N' -> y - 1 >= 0
            'E' -> x + 1 < this.first().size
            'S' -> y + 1 < this.size
            'W' -> x - 1 >= 0
            else -> false
        }
    }

    private fun Char.change(): Char {
        return when (this) {
            'N' -> 'E'
            'E' -> 'S'
            'S' -> 'W'
            'W' -> 'N'
            else -> ' '
        }
    }
}




