package com.github.checketts.palindromes

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import java.lang.IllegalStateException
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.atomic.AtomicLong

data class Submitter(
        val name: String,
        val id: String,
)

data class PalindromeDetails(
        val text: String,
        val normalized: String,
        val createdAt: Instant,
        val submittedById: String,
        val votes: AtomicLong = AtomicLong(0)
)

@Service
class PalindromeService {
    val maxTopDurationMs = AtomicLong(0)

    val alice = Submitter("Alice", UUID.randomUUID().toString())
    val bob = Submitter("Bob", UUID.randomUUID().toString())
    val carl = Submitter("Carl", UUID.randomUUID().toString())

    val submitters = mutableListOf(alice, bob, carl)

    val palindromeList = mutableListOf(
            PalindromeDetails("racecar", "racecar", Instant.now().minus(10, ChronoUnit.DAYS), alice.id),
            PalindromeDetails("Step on no pets", "steponnopets", Instant.now().minus(10, ChronoUnit.DAYS), alice.id),
            PalindromeDetails("Mom", "mom", Instant.now().minus(10, ChronoUnit.DAYS), bob.id),
            PalindromeDetails("Dad", "dad", Instant.now().minus(10, ChronoUnit.DAYS), carl.id),
    )





    fun fetchAPalindrome(): PalindromeDetails {
        return palindromeList.random()
    }

    fun addNewPalindrome(palindromeText: String, submitter: String): PalindromeDetails {
        val normalized = palindromeText.replace("[^a-zA-Z\\d]*", "")

        val alreadyExists = palindromeList.firstOrNull { it.normalized == normalized } != null
        if(alreadyExists) {
            throw IllegalStateException("A palindrome matching '$normalized' already exists")
        }
        val submitter = findSubmitter(submitter)
        val newPal = PalindromeDetails(palindromeText, normalized, Instant.now(), submitter.id)
        palindromeList.add(newPal)

        return newPal
    }

    fun getAPalindromeById(palindromeId: String): PalindromeDetails {
        return palindromeList.firstOrNull { it.normalized.equals(palindromeId, ignoreCase = true) }
                ?: throw PalindromeController.NotFoundResponse("Palindrome with id of $palindromeId")
    }

    fun recordVote(palindromeId: String): Long {
        return palindromeList.firstOrNull { it.normalized == palindromeId }?.votes?.incrementAndGet() ?: 0
    }

    fun fetchTopPalindromes(): List<PalindromeDetails> {
        val sleep = (100..1000L).random()
        maxTopDurationMs.getAndUpdate { if(it < sleep) sleep else it }

        Thread.sleep(sleep)
        return palindromeList.sortedByDescending { it.votes.get() }.take(5)
    }


    @GetMapping("palindrome/submitted-by/{id}")
    fun getBySubmittedId(@PathVariable id: String): List<PalindromeDetails> {
        return palindromeList.filter { it.submittedById == id }
    }

    private fun findSubmitter(submitterName: String): Submitter {
        val submitter = submitters.firstOrNull { it.name == submitterName }
        if(submitter != null) {
            return submitter
        }

        val newSubmitter = Submitter(submitterName, UUID.randomUUID().toString())
        submitters.add(newSubmitter)
        return newSubmitter
    }


}
