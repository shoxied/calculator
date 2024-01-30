package com.example.calculator

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import java.lang.Exception

class SolutionTest {
    @Test
    fun solve_simple_number() {
        assertEquals(2.0, Solution().solve("2"), 0.001)
    }

    @Test
    fun solve_few_number() {
        assertEquals(205.0, Solution().solve("205"), 0.001)
    }

    @Test
    fun solve_decimal_number() {
        assertEquals(205.15, Solution().solve("205.15"), 0.001)
    }

    @Test
    fun solve_one_decimal_number() {
        assertEquals(0.2, Solution().solve("0.2"), 0.001)
    }

    @Test
    fun solve_summ() {
        assertEquals(4.0, Solution().solve("2+2"), 0.001)
    }

    @Test
    fun solve_summ_dif() {
        assertEquals(4.98, Solution().solve("2.2+2.78"), 0.001)
    }

    @Test
    fun solve_min() {
        assertEquals(3.58, Solution().solve("5.78-2.2"), 0.001)
    }

    @Test
    fun solve_times() {
        assertEquals(42.0, Solution().solve("6*7"), 0.001)
    }

    @Test
    fun solve_div() {
        assertEquals(0.333, Solution().solve("1/3"), 0.001)
    }

    @Test
    fun solve_lots_summ() {
        assertEquals(8.0, Solution().solve("2+2+2+2"), 0.001)
    }

    @Test
    fun solve_summ_min() {
        assertEquals(4.0, Solution().solve("2+2-2+2"), 0.001)
    }

    @Test
    fun solve_summ_min_mult() {
        assertEquals(-2.0, Solution().solve("2+2-2*3"), 0.001)
    }

    @Test
    fun solve_div_min_mult() {
        assertEquals(-5.0, Solution().solve("2/2-2*3"), 0.001)
    }

    @Test
    fun solve_div_min_mult_bracket() {
        assertEquals(6.0, Solution().solve("2/(3-2)*3"), 0.001)
    }

    @Test
    fun solve_div_min_mult_bracket_err() {
        try {
            Solution().solve("2/3-2)*3")
            fail()
        } catch (error: SolutionError){
            assertEquals("Ошибка", error.message)
        }
    }

    @Test
    fun solve_multi_bracket() {
        assertEquals(7.0, Solution().solve("(2*(10-3))/2"), 0.001)
    }
}