package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File

class Day6 {
    private val testFilePath = INPUTS_PATH + "day6-test.txt"
    private val realDataPath = INPUTS_PATH + "day6.txt"

    fun result(isRealTry: Boolean = false, marker: Marker = Marker.START_OF_PACKET): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        lines.forEach {
            detect(it, marker)
        }
        return 2
    }

    private fun detect(it: String, marker: Marker) {
        var chars = mutableListOf<Char>()

        for (i in 0 until it.toCharArray().size) {
            val curChar = it[i]
            if(chars.contains(curChar)) {
                val indexOf = chars.indexOf(curChar)
                chars = chars.subList(indexOf + 1 , chars.size)
                chars.add(curChar)
                continue
            }
            chars.add(curChar)
            if(chars.size == marker.value) {
                println(i + 1)
                break
            }
        }
    }

    enum class  Marker(val value: Int) {
        START_OF_PACKET(4), START_OF_MESSAGE(14)
    }




}