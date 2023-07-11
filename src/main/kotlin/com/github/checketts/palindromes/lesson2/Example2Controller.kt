package com.github.checketts.palindromes.lesson2

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid
import javax.validation.constraints.Max

@Controller
class Example2Controller {

    @GetMapping("/example2")
    fun getLesson2(): String {
        return "lesson2/lesson2"
    }


    data class Person(
        @field:Max(value=10, message = "{person.name.Max.message}") val name: String,
    )


    @GetMapping("/example2-finished")
    fun getLesson2(
        @RequestParam name: String,
        @Valid @ModelAttribute person: Person,
        bindingResult: BindingResult,
        model: Model
    ): String {
        model["name"] = name

        return "lesson2/lesson2-finished"
    }

}