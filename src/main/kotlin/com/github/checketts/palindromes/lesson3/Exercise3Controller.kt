package com.github.checketts.palindromes.lesson3

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Exercise3Controller {

    @GetMapping("/exercise3")
    fun getExercise3(): String {
        return "lesson3/exercise3"
    }

}