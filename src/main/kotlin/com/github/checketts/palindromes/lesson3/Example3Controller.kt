package com.github.checketts.palindromes.lesson3

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Example3Controller {

    @GetMapping("/example3")
    fun getLesson3(): String {
        return "lesson3/lesson3"
    }


    @GetMapping("/example3-finished")
    fun getLesson3Finished(): String {
        return "lesson3/lesson3-finished"
    }

}