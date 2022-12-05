package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File

class Day4 {
    private val testFilePath = INPUTS_PATH + "day4-test.txt"
    private val realDataPath = INPUTS_PATH + "day4.txt"

    fun result(isRealTry: Boolean = false): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        var score = 0;
        lines.forEach {
          val res = it.split(",")
            score += ifPairContainAnother(res)
        }
        return score
    }


    fun ifPairContainAnother(pairs: List<String>): Int {
        val elf1 = pairs[0].split("-")
        val elf2 = pairs[1].split("-")
        return if (overlaps(elf1, elf2)) 1 else 0
    }


    fun fullyContains(pair: List<String>, par2: List<String>): Boolean {
        return pair[0].toInt() >= par2[0].toInt() && par2[1].toInt() >= pair[1].toInt() ||
                par2[0].toInt() >= pair[0].toInt() && pair[1].toInt() >= par2[1].toInt()
    }
    fun overlaps(pair: List<String>, pair2: List<String>): Boolean {
        return pair[0].toInt() >= pair2[0].toInt() && pair[0].toInt() <= pair2[1].toInt() ||
             pair2[0].toInt() >= pair[0].toInt() && pair2[0].toInt() <= pair[1].toInt()
    }

}
