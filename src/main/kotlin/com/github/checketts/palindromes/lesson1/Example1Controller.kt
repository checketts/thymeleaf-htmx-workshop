package com.github.checketts.palindromes.lesson1

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class Example1Controller {

    @GetMapping("/example1")
    fun getLesson1() {

    }












    @GetMapping("/example1-finished")
    fun getLesson1Finished(
        @RequestParam total: Int?,
        model: Model,
    ): String {
        model["total"] = total ?: 0

        return "lesson1/lesson1-finished"
    }

}