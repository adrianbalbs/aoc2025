package org.example

fun main() {
    val lines = object {}.javaClass
        .getResourceAsStream("/inputs/day2")
        ?.bufferedReader()
        ?.readLines()
        ?.joinToString("") ?: ""

    val nums = lines
        .split(',')
        .map(::transformToRangeList)
        .flatten()

    val day1 = nums
        .filter(::isInvalidIdPt1)
        .sum()
    println("Day 1: $day1")

    val day2 = nums
        .filter(::isInvalidIdPt2)
        .sum()
    println("Day 2: $day2")
}

fun transformToRangeList(input: String): List<Long> {
    val (low, high) = input.split('-')
    return (low.toLong()..high.toLong()).toList()
}

fun isInvalidIdPt1(id: Long): Boolean {
    val idStr = id.toString()
    return idStr == idStr.take(idStr.length / 2).repeat(2)
}

fun isInvalidIdPt2(id: Long): Boolean {
    // check for sequences whose lengths are divisors of the length of the entire string
    // pattern will be at most half the len of the str, if it's larger than that, then it is impossible to get a split
    // here we concatenate the pattern i amount of times to get the expected pattern and see if it matches the original string
    val idStr = id.toString()
    return (1..idStr.length / 2)
        .filter { i -> idStr.length % i == 0 }
        .any { i -> idStr == idStr.take(i).repeat(idStr.length / i) }
}
