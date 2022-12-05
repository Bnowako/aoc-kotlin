package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File

class Day5 {
    private val testFilePath = INPUTS_PATH + "day5-test.txt"
    private val realDataPath = INPUTS_PATH + "day4.txt"

    fun result(isRealTry: Boolean = false): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        val indexOfSeparator = lines.indexOfFirst { it == "" }
        val list = mutableListOf<MutableList<Char>>()
        for (i in indexOfSeparator - 2 downTo 0) {
            val line = lines[i]
            for (j in 1 until line.lastIndex step 4) {
                val listIdx = if (j == 1) 0 else (j - 1) / 4
                var curList = list.getOrNull(listIdx)
                if (curList == null) {
                    list.add(mutableListOf())
                    curList = list[listIdx]
                }

                val char = line[j]
                if (char == ' ') continue
                curList.add(char)
            }
        }


        return 4
    }


}
