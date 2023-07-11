package com.github.checketts.palindromes.lesson6

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@Controller
class Example6Controller {

    @GetMapping("/example6")
    fun getLesson6() {

    }





    @GetMapping("/example6-finished")
    fun getLesson6Finished(
        @RequestParam total: Int?,
        @RequestHeader("HX-Request") htmxRequest: Boolean?,
        model: Model,
    ): String {
        model["total"] = total ?: 0

        if(htmxRequest == true) {
            return "lesson6/lesson6-finished :: total"
        } else {
            return "lesson6/lesson6-finished"
        }
    }

}