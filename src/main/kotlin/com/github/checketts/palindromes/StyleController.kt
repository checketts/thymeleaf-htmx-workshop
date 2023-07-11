package com.github.checketts.palindromes

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Controller
class StyleController(
        private val viewResolver: ThymeleafViewResolver
) {

    @GetMapping("styles/progressbar.css")
    fun getComponentStyles(req: HttpServletRequest, resp: HttpServletResponse) {
        val v = viewResolver.resolveViewName("progressbar/progressbar.css", Locale.ENGLISH) ?: error("View not found")
        v.render(mapOf<String,String>(), req, resp)
    }

}
