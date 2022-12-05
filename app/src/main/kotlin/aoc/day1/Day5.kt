package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File
import kotlin.math.hypot

class Day5 {
    private val testFilePath = INPUTS_PATH + "day5-test.txt"
    private val realDataPath = INPUTS_PATH + "day5.txt"

    fun result(isRealTry: Boolean = false): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        val stacks = parseLines(lines)
        val commands = parseCommands(lines)
        crateMover9001(stacks, commands)
        printStacks(stacks)
        return 1
    }

    private fun printStacks(stacks: MutableList<MutableList<Char>>) {
       stacks.forEach {
           print(it.last())
       }
    }

    private fun applyCommands(stacks: MutableList<MutableList<Char>>, commands: List<List<Int>>) {
       commands.forEach {
           val q = it[0]
           val from = stacks[it[1] - 1]
           val to = stacks[it[2] - 1]
           for(i in 0 until q) {
               val removed = from.removeLast()
               to.add(removed)
           }
       }
    }

    private fun crateMover9001(stacks: MutableList<MutableList<Char>>, commands: List<List<Int>>) {
        commands.forEach {
            val q = it[0]
            val from = stacks[it[1] - 1]
            val to = stacks[it[2] - 1]
            val removedCrates = mutableListOf<Char>()
            for(i in 0 until q) {
                 removedCrates.add(from.removeLast())
            }
            to.addAll(removedCrates.reversed())
        }
    }


    private fun parseCommands(lines: List<String>): List<List<Int>> {
        val indexOfSeparator = lines.indexOfFirst { it == "" }
        val output = mutableListOf<List<Int>>()
        for(i in indexOfSeparator + 1 ..  lines.lastIndex) {
            val curLine = lines[i]
            val regex = """[A-Za-z]+""".toRegex()
            val a = curLine.replace(regex, "").split(" ")
            val commands = a.filter { it != "" }.map { it.toInt() }
            output.add(commands)
        }
        return output
    }

    fun parseLines(lines: List<String>): MutableList<MutableList<Char>> {
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
        return list
    }


}
