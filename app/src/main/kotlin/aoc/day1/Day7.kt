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
                        val entityName = command[2]
                        if(currentNode.children.contains(entityName)) {
                            currentNode = currentNode.children[entityName]!!
                        } else {
                            val added = root.addChild(entityName, 0)
                            currentNode = added
                        }
                        pwd.add(entityName)
                    }
                } else if (command[1] == "ls") {
                    inLs = true
                }
            } else if(inLs) {
                val output = it.split(" ")
                if(output[0] == "dir"){
                    currentNode.addChild(output[1], 0)
                }else {
                    currentNode.addChild(output[1], output[0].toInt())
                }
            }
        }

        val occupiedSpace = root.size
        val total = 70000000
        val needed = 30000000
        val unused = total - occupiedSpace
        val weHaveToFree = needed - unused
        val smallest = root.findSmallestPossible(weHaveToFree, root)
        println(smallest?.size)
        return 1
    }

    class Node(val name: String, var size: Int = 0, val parent: Node? = null, val children: HashMap<String,Node> = hashMapOf()) {
        fun addChild(name: String, size: Int): Node {
            updateParents(size)
            val element = Node(name, size, this)
            children[name] = element
            return element
        }

        fun updateParents(size: Int) {
            this.size += size
            parent?.updateParents(size)
        }

        fun traverseAndSum(): Int {
            var sum = 0;
            children.values.forEach {
                if(it.children.isNotEmpty()) {
                    if(it.size <= 100000) {
                        println(it.name)
                        sum += it.size
                    }
                    sum += it.traverseAndSum()
                }
            }
            return sum;
        }

        fun findSmallestPossible(weHaveToFree: Int, root: Node): Node {
            var bestOption = root
            children.values.forEach {
                if(it.children.isNotEmpty()) {
                    if(it.size >= weHaveToFree && it.size < bestOption.size) {
                        bestOption = it
                    }
                    bestOption = it.findSmallestPossible(weHaveToFree, bestOption)
                }
            }
            return bestOption
        }

    }






}
