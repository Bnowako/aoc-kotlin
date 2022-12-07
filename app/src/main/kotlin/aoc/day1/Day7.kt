package aoc.day1

import aoc.utils.INPUTS_PATH
import java.io.File

class Day7 {
    private val testFilePath = INPUTS_PATH + "day7-test.txt"
    private val realDataPath = INPUTS_PATH + "day7.txt"

    fun result(isRealTry: Boolean = false): Int {
        val lines = File(if (isRealTry) realDataPath else testFilePath).useLines { it.toList() }
        var inLs = false
        val root: Node = Node("root")
        var currentNode: Node = root

        var pwd = mutableListOf<String>()
        lines.forEach {
            if(it.startsWith("$")) {
                inLs = false
                val command = it.split(" ")
                if(command[1] == "cd") {
                     if(command[2] == "..") {
                         pwd.removeLast()
                         currentNode = currentNode.parent!!
                     } else {
                         pwd.add(command[2])
                     }
                } else if (command[1] == "ls") {
                    inLs = true
                }
            }

            if(inLs) {
                val output = it.split(" ")
                if(output[0] == "dir"){
                    currentNode.addChild(output[1], 0)
                }else {
                    currentNode.addChild(output[1], output[0].toInt())
                }
            }


        }
        return 1
    }

    data class Node(val name: String, var size: Int = 0, val parent: Node? = null, val children: MutableList<Node> = mutableListOf()) {
        fun addChild(name: String, size: Int) {
            children.add(Node(name, size, this))
        }

    }




}
