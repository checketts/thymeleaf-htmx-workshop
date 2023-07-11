package com.github.checketts.palindromes.lesson5

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class Example5Controller {

    data class MenuItem(
        val name: String,
        val price: Double,
        val group: String,
    )

    @GetMapping("/example5")
    fun getLesson5(
        @ModelAttribute("food") food: MenuItem?,
        bindingResult: BindingResult,
        model: Model,
    ): String {
        if(food == null) {
            model["food"] = MenuItem("", 0.0, "")
        }

        return "lesson5/lesson5"
    }


    @GetMapping("/example5-finished")
    fun getLesson5Finished(
        @ModelAttribute("food") food: MenuItem?,
        bindingResult: BindingResult,
        model: Model,
    ): String {
        if(food == null) {
            model["food"] = MenuItem("", 0.0, "")
        }

        return "lesson5/lesson5-finished"
    }

}