package com.github.checketts.palindromes.lesson6

import io.github.wimdeblauwe.hsbt.mvc.HtmxRequest
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import javax.servlet.http.HttpServletResponse

@Controller
class Exercise6Controller {

    val students = listOf(
        Student(1,"Alice", 4.0, 3.7, 3.0),
        Student(2, "Bob", 1.9, 2.2, 4.0),
        Student(3, "Carl", 3.9, 4.0, 3.3),
    )
    val studentIds = students.map { StudentNameAndId(it.id, it.name) }

    data class StudentNameAndId(
        val id: Long,
        val name: String,
    )

    data class Student(
        val id: Long,
        val name: String,
        val englishGrade: Double,
        val mathGrade: Double,
        val scienceGrade: Double,
    )

    @GetMapping("/exercise6")
    fun getExercise6(resp: HttpServletResponse) {
        resp.sendRedirect("/exercise6/${students.first().id}")
    }

    @GetMapping("/exercise6/{id}")
    fun getExercise6Student(
        @PathVariable id: Long,
        htmxReq: HtmxRequest,
        model: Model,
    ): String {
        model["id"] = id
        model["students"] = studentIds
        model["student"] = students.first { it.id == id }

        return "lesson6/exercise6"
    }

}