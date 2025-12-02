package org.example

fun main() {
    val lines = object {}.javaClass.getResourceAsStream("/inputs/day1")?.bufferedReader()?.readLines() ?: emptyList()
    println("Part 1: ${partOne(lines)}")
    print("Part 2: ${partTwo(lines)}")
}

fun partOne(lines: List<String>): Int {
    var dialPosition = 50
    var password = 0
    for (line in lines) {
        dialPosition = (dialPosition + run {
            val (direction, turns) = lineToDirectionTuple(line)
            when (direction) {
                'L' -> -turns
                else -> turns
            }
        }).mod(100)
        if (dialPosition == 0) {
            password++
        }
    }
    return password
}

fun partTwo(lines: List<String>): Int {
    var dialPosition = 50
    var password = 0
    for (line in lines) {
        val (dir, amt) = lineToDirectionTuple(line)
        // Numbers here are not so big... just brute force it lmao
        for (i in 0..<amt) {
            when (dir) {
                'L' -> dialPosition = (dialPosition - 1) % 100
                'R' -> dialPosition = (dialPosition + 1) % 100
            }
            if (dialPosition == 0) password++
        }
    }
    return password
}


fun lineToDirectionTuple(line: String): Pair<Char, Int> {
    return line.first() to line.substring(1).toInt()
}

