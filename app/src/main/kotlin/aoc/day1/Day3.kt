package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File
import kotlin.streams.toList

class Day3 {
    private val testFilePath = INPUTS_PATH + "day3-test.txt"
    private val realDataPath = INPUTS_PATH + "day3.txt"

    fun result(isRealTry: Boolean = false): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        var score = 0;
        lines.forEach {
            val firstCompartment = it.toCharArray().toList().subList(0, it.length / 2)
            val secondCompartment = it.toCharArray().toList().subList(it.length / 2, it.length)
            score += score(firstCompartment, secondCompartment)
        }
        return score
    }

    fun scoreGroups(isRealTry: Boolean = false): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        var commonLetters = hashMapOf<Char, Int>()
        var score = 0;
        for (i in lines.indices step 3) {
           val subLines = lines.subList(i, i + 3)
            subLines.forEach { it ->
                it.toCharArray().toHashSet().forEach  {char ->
                   commonLetters[char] = (commonLetters[char] ?: 0) + 1
                }
            }
            val values = commonLetters.filterValues { it == 3 }
            values.keys.forEach {
                score += scoreChar(it)
            }
            commonLetters = hashMapOf()
        }
        return score
    }


    fun score(first: List<Char>, second: List<Char>): Int {
        var score: Int = 0;
        val firstSet = first.toHashSet()
        val charsToScore = hashSetOf<Char>();
        second.forEach {
            if (firstSet.contains(it)) {
                 charsToScore.add(it)
            }
        }
        charsToScore.forEach {
            score += scoreChar(it)
        }
        return score
    }

    private fun scoreChar(char: Char): Int {
        return if(char.code < 97) {
            char.code - 38
        } else {
            char.code - 96
        }
    }


}
