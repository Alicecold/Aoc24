import java.io.File

class Day04 {
    fun run() {
        val input1 = File("src/main/resources/actual/day03.txt")
        val test = File("src/main/resources/test/day04test.txt")
        val test2 = File("src/main/resources/test/day03test02.txt")
        println(part1(test))
        //println(part2(test2))
    }

    private fun part1(input: File): Int {
        val lines = input.useLines { it.toList() }

        return lines.sumOf { it.getXmas() } + lines.transpose().sumOf { it.getXmas() } + lines.shear().sumOf { it.getXmas() }
    }

    private fun String.getXmas(): Int {
        val regex = Regex("XMAS")
        return regex.findAll(this).count() + regex.findAll(this.reversed()).count()
    }

    fun List<String>.transpose(): List<String> {
        return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] }.joinToString("") }
    }

    /* TODO: How to take the diagonal? */
    /* (0,0) (1,0) (2,0)*/
    /* (0,1) (1,1) (2,1)*/
    /* (0,2) (1,2) (2,2)*/
    /* (i, j) */

    /* (0,0) (1,1) (2,2)*/ /* (i, j+i) (i, j+i) (i, i+j) */
    /* (1,0) (2,1)  */ /* (i+1, j) (i+1, j)*/
    /* (2,0) */ /* (i+2, j) */

    /* (0,1) (1,2) *//* (i, j+1) (i, j+1)*/
    /* (0,2) */ /* (i, j+2) */

    private fun List<String>.shear(): List<String> {
        return (this[0].indices).map { i ->
            (this.indices).map { j ->
                this[i][j]}.joinToString("")
        }
    }
}




