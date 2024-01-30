package com.example.calculator

import android.util.Log
import kotlin.math.pow

class Solution {
    private val priorityOperation = mapOf('+' to 1, '-' to 1, '*' to 2, '/' to 2)

    fun solve(expression: String): Double {

        var pos = 0
        val nums: MutableList<Double> = mutableListOf()
        val operators: MutableList<Char?> = mutableListOf()
        val priorities: MutableList<Int> = mutableListOf()

        var dot = false
        var dotCount = 0
        nums.add(0.0)

        var currentPriority = 0

        for (i in expression) {
            if (i.isDigit()) {
                if (dot) {
                    nums[pos] += (i.code - 48) / ((10.0).pow(++dotCount))
                } else {
                    nums[pos] = nums[pos] * 10 + i.code - 48
                }
            } else if (i == '.') {
                dot = true
            } else if (setOf('+', '-', '*', '/', '(', ')').contains(i)) {
                if (i == '('){
                    currentPriority++
                }
                else if (i == ')'){
                    currentPriority--
                }
                else {
                    pos++
                    nums.add(0.0)
                    operators.add(i)
                    priorities.add(currentPriority)
                }
                dot = false
                dotCount = 0
            }
        }

        while (operators.isNotEmpty()) {
            for (i in 0 until operators.size) {
                if ((i == (operators.size - 1)) ||
                    (((priorityOperation[operators[i]]?:0) + priorities[i] * 10) >= ((priorityOperation[operators[i + 1]]?:0) + priorities[i + 1] * 10))
                ) {
                    if (operators[i] == '+') {
                        nums[i] += nums[i + 1]
                    } else if (operators[i] == '-') {
                        nums[i] -= nums[i + 1]
                    } else if (operators[i] == '*') {
                        nums[i] *= nums[i + 1]
                    } else if (operators[i] == '/') {
                        nums[i] /= nums[i + 1]
                    }
                    operators.removeAt(i)
                    nums.removeAt(i + 1)
                    priorities.removeAt(i)
                    break
                }
            }
        }

        var bracketCount = 0
        for (i in expression){
            if (i == '(' || i == ')'){
                bracketCount++
            }
        }
        if (bracketCount % 2 != 0) throw SolutionError("Нет скобки")

        return nums[0]
    }
}