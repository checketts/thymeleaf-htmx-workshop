package com.github.checketts.palindromes

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.http.HttpServletResponse

@Controller
class RootController {

    @GetMapping("/")
    fun root(resp: HttpServletResponse){
        resp.sendRedirect("palindromes")
    }
}
