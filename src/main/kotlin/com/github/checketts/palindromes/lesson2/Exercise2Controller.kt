package com.github.checketts.palindromes.lesson2

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Exercise2Controller {

    data class Student(
        val englishGrade: Float,
        val mathGrade: Float,
        val scienceGrade: Float,
    )

    @GetMapping("/exercise2")
    fun getExercise2(): String {
        return "lesson2/exercise2"
    }

}