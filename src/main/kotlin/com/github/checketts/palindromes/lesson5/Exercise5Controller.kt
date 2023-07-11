package com.github.checketts.palindromes.lesson5

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Exercise5Controller {

    val students = listOf(
        Student("Alice", 4.0, 3.7, 3.0),
        Student("Bob", 1.9, 2.2, 4.0),
        Student("Carl", 3.9, 4.0, 3.3),
    )

    data class Student(
        val name: String,
        val englishGrade: Double,
        val mathGrade: Double,
        val scienceGrade: Double,
    )

    @GetMapping("/exercise5")
    fun getExercise5(
        model: Model,
    ): String {
        model["students"] = students

        return "lesson5/exercise5"
    }

}