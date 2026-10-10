package com.Ustenko.data

import kotlin.random.Random

object ListGenerator {


    fun generate(): List<Int> {
        return List(10) { Random.nextInt(-50, 101) }
    }
}
