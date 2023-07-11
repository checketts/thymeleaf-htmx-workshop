package com.github.checketts.palindromes.lesson7

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Exercise7Controller {

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

    @GetMapping("/exercise7")
    fun getExercise7(
        model: Model,
    ): String {
        model["students"] = students

        return "lesson6/exercise6"
    }

}