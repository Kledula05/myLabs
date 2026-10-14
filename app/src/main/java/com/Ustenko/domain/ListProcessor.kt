package com.Ustenko.domain

object ListProcessor {

    fun replace(list: List<Int>, z: Int): List<Int> {
        return list.map { if (it > z) z else it }
    }

    fun countReplacements(list: List<Int>, z: Int): Int {
        return list.count { it > z }
    }

    fun getResult(list: List<Int>, input: String): String {
        val z = input.toIntOrNull()
        if (z == null) {
            return "Ошибка: введите целое число Z"
        }

        val count = countReplacements(list, z)
        if (count == 0) {
            return "Элементов больше $z не найдено"
        }

        val newList = replace(list, z)
        return "Новый список: " + newList.joinToString(", ") + "\nКоличество замен: $count"
    }
}
