package com.Ustenko.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ListProcessorTest {

    @Test
    fun replace_isCorrect() {
        val result = ListProcessor.replace(listOf(5, 12, -3, 20, 10), 10)
        assertEquals(listOf(5, 10, -3, 10, 10), result)
    }

    @Test
    fun countReplacements_isCorrect() {
        assertEquals(2, ListProcessor.countReplacements(listOf(5, 12, -3, 20, 10), 10))
    }

    @Test
    fun getResult_notFound() {
        assertEquals("Элементов больше 10 не найдено", ListProcessor.getResult(listOf(1, 2, 3), "10"))
    }

    @Test
    fun getResult_wrongInput() {
        assertEquals("Ошибка: введите целое число Z", ListProcessor.getResult(listOf(1, 2, 3), "abc"))
    }
}
