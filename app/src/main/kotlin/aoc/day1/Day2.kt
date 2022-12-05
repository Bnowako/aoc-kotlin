package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File

class Day2 {
    private val testFilePath = INPUTS_PATH + "day2-test.txt"
    private val realDataPath = INPUTS_PATH + "day2-test.txt"

    fun result(isRealTry: Boolean = false): Int {
        var score = 0;
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        lines.forEach {
            score += getScore(it.toCharArray()[0], it.toCharArray()[2])
        }
        return 1
    }


    enum class Outcome {
        WIN, DRAW, LOOSE
    }

    private fun getOutcome(elf: Char, our: Char): Outcome {
        if (elf == our) return Outcome.DRAW;
        return when (elf) {
            'R', 'P', 'S' -> Outcome.WIN
            else -> Outcome.LOOSE
        }
    }

    private fun getScore(elf: Char, our: Char): Int {
        return when (getOutcome(elf, our)) {
            Outcome.WIN -> 6
            Outcome.DRAW -> 3
            Outcome.LOOSE -> 0
        }
    }

}
