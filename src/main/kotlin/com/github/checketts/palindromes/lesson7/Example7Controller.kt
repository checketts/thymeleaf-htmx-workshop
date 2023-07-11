package com.github.checketts.palindromes.lesson7

import io.github.wimdeblauwe.hsbt.mvc.HtmxResponse
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.concurrent.ConcurrentHashMap
import javax.servlet.http.HttpServletResponse
import javax.validation.constraints.Max

@Controller
class Example7Controller {

    val foods = ConcurrentHashMap<Long, MenuItem>().apply {
        put(1, MenuItem(1, "Orange Chicken", 4.25, "Chinese"))
        put(2, MenuItem(2, "Peanut Butter and Jelly Sandwich", 1.25, "Home-made"))
        put(3, MenuItem(3, "Kung Pao Chicken", 4.75, "Chinese"))
        put(4, MenuItem(4, "Fries", 3.25, "Fast"))
    }

    data class MenuItem(
        val id: Long = -1L,
        @field:Max(20) val name: String,
        val price: Double,
        @field:Max(20) val group: String,
    )

    @GetMapping("/example7")
    fun getLesson7(
        model: Model,
    ): String {
        model["groups"] = fetchGroupCounts()


        return "lesson7/lesson7"
    }

    @GetMapping("/example7/{groupName}")
    fun getLesson7ByGroupName(
        @PathVariable groupName: String,
        model: Model,
    ): String {
        model["groups"] = fetchGroupCounts()
        model["groupsName"] = groupName
        model["group"] = fetchGroupByName(groupName)

        return "lesson7/lesson7"
    }

    @PostMapping("/example7")
    fun addOrUpdateFood(
        @RequestParam name: String,
        @ModelAttribute("food") food: MenuItem,
        bindingResult: BindingResult,
        model: Model,
        resp: HttpServletResponse,
    ) {
        if(bindingResult.hasErrors()) {
            // return errors
        } else {
            val savingFood = if(food.id == -1L) {
                food.copy(id=foods.entries.maxOf { it.value.id }+1)
            } else {
                food
            }
            foods[savingFood.id] = savingFood

        }

        resp.sendRedirect("/example7/${food.group}")
    }

    private fun fetchGroupCounts(): Map<String, Int> {
        Thread.sleep(300)
        val groups = foods.entries.map{it.value}.groupBy { it.group }
        return groups.map { it.key to it.value.size }.toMap()
    }


    private fun fetchGroupByName(groupName: String): List<MenuItem> {
        Thread.sleep(300)
        return foods.entries.map{it.value}.filter { it.group == groupName }.sortedBy { it.name }
    }

    @GetMapping("/example7-finished")
    fun getLesson7Finished(
        model: Model,
    ): String {
//        model["groups"] = fetchGroupCounts()
        model["sidebarTrigger"] = "load"
        model["food"] = MenuItem(-1L, "", 0.0, "")

        return "lesson7/lesson7-finished"
    }

    @GetMapping("/example7-counts-finished")
    fun getLesson7CountsFinished(
        model: Model,
    ): String {
        model["groups"] = fetchGroupCounts()
        model["sidebarTrigger"] = "UpdateCounts from:body"
        return "lesson7/lesson7-finished :: sidebar"
    }

    @GetMapping("/example7-finished/{groupName}")
    fun getLesson7ByGroupNameFinished(
        @PathVariable groupName: String,
        model: Model,
    ): String {
//        model["groups"] = fetchGroupCounts()
        model["sidebarTrigger"] = "load"
        model["groupsName"] = groupName
        model["group"] = fetchGroupByName(groupName)
        model["food"] = MenuItem(-1L, "", 0.0, "")

        return "lesson7/lesson7-finished"
    }

    @PostMapping("/example7-finished")
    fun addOrUpdateFoodFinished(
        @ModelAttribute("food") food: MenuItem,
        @RequestParam name: String,
        bindingResult: BindingResult,
        model: Model,
    ): HtmxResponse {
        val resp= HtmxResponse()

        if(bindingResult.hasErrors()) {
            // return errors
        } else {
            val savingFood = if(food.id == -1L) {
                food.copy(id=foods.entries.maxOf { it.value.id }+1)
            } else {
                food
            }
            foods[savingFood.id] = savingFood
            resp.addTrigger("UpdateCounts")
            resp.addTemplate("lesson7/lesson7-finished :: foodForm")
        }

        return resp
    }

}