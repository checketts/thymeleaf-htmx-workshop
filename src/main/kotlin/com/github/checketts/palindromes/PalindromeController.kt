package com.github.checketts.palindromes

import io.github.wimdeblauwe.hsbt.mvc.HtmxRequest
import io.github.wimdeblauwe.hsbt.mvc.HtmxResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.lang.IllegalStateException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.validation.Valid
import javax.validation.Validator
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Controller
@RequestMapping("/palindromes")
class PalindromeController(
        private val palindromeService: PalindromeService,
        private val validator: Validator,
) {
    val executor = Executors.newFixedThreadPool(5)

    class NotFoundResponse(msg: String): RuntimeException(msg)

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not Found")
    @ExceptionHandler(NotFoundResponse::class)
    fun notFoundHandler() {}

    @GetMapping
    fun getAPalindrome(
            model: Model,
    ): String {
        model["p"] = palindromeService.fetchAPalindrome()
        model["form"] = NewPalindromeRequest("", "")

        val top = executor.submit(Callable { palindromeService.fetchTopPalindromes() })
        try {
            model["topPalindromes"] = top.get(200, TimeUnit.MILLISECONDS)
            model["topPalidromeTrigger"]= "every 30000ms"
        } catch (e: TimeoutException) {
            model["topPalidromeTrigger"]= "load"
        }

        return "palindromes"
    }

    @GetMapping("top")
    fun getTopPalindromes(
            model: Model,
    ): String {
        model["topPalidromeTrigger"]= "every 30s, voteAdded from:body"
        model["topPalindromes"] = palindromeService.fetchTopPalindromes()
        return "palindromes :: topPalindromes"
    }

    @GetMapping("random")
    fun getARandomPalindrome(
            model: Model,
    ): String {
        model["p"] = palindromeService.fetchAPalindrome()
        return "palindromes :: randomPalindrome"
    }

    data class NewPalindromeRequest(
            @get:NotBlank(message = "Palindrome text is required")
            @get:Size(max=50)
            val palindromeText: String = "",
            @get:NotBlank(message = "Submitter is required")
            @get:Size(max = 25)
            val submitter: String = ""
    )

    @GetMapping("{palindromeId}")
    fun getAPalindromeById(
            @PathVariable palindromeId: String,
            model: Model,
    ): String {
        model["p"] = palindromeService.getAPalindromeById(palindromeId)
        return "palindromes :: randomPalindrome"
    }


    @PostMapping("vote/{palindromeId}")
    fun addVote(
            @PathVariable palindromeId: String,
            model: Model,
    ): HtmxResponse? {
        palindromeService.recordVote(palindromeId)

        val view = getAPalindromeById(palindromeId, model)
        return HtmxResponse().addTemplate(view).addTrigger("voteAdded")
    }


    @PostMapping
    fun postNewPalindrome(
            @Valid @ModelAttribute("form") form: NewPalindromeRequest,
            bindingResult: BindingResult,
            model: Model,
            htmxReq: HtmxRequest,
    ): String {
        val palindromeLetters = form.palindromeText.lowercase().replace("[^A-Za-z0-9]".toRegex(), "")
        val isPalindrome = palindromeLetters.reversed() == palindromeLetters
        if(!isPalindrome) {
            bindingResult.rejectValue("palindromeText", "notAPalindrome", "That isn't a palindrome!")
        }

        if(htmxReq.triggerName == null && !bindingResult.hasErrors()) {
            try {
                palindromeService.addNewPalindrome(form.palindromeText, form.submitter)
                model["addMessage"] = "New palindrome '${form.palindromeText}' has been added!"
                model["form"] = NewPalindromeRequest()
            } catch (e: IllegalStateException) {
                bindingResult.rejectValue("palindromeText", "duplicatePalidrome", "The balindrome '${form.palindromeText}' already exists")
            }
        }

        return if(htmxReq.triggerName == "palindromeText") {
            "palindromes :: palindromeTextField"
        } else if(htmxReq.triggerName == "submitter"){
            "palindromes :: submitterField"
        } else {
            "palindromes :: addForm"
        }
    }


}
