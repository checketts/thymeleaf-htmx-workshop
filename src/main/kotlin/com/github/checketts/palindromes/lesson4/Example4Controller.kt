package com.github.checketts.palindromes.lesson4

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class Example4Controller {

    val menuItems = mutableListOf(
        Item("Hamburger", 4.0),
        Item("Shake", 3.74),
        Item("Fries", 2.3),
    )

    data class Item(
        val name: String,
        val price: Double,
    )

    @GetMapping("/example4")
    fun getLesson4() {

    }



    @GetMapping("/example4-finished")
    fun getLesson4Finished(
        model: Model,
    ): String {
        model["menuItems"] = menuItems

        return "lesson4/lesson4-finished"
    }

}