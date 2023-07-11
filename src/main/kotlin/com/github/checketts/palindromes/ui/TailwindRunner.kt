package com.github.checketts.palindromes.ui

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.atomic.AtomicBoolean
import javax.annotation.PreDestroy

private val logger = mu.KotlinLogging.logger {  }

@Component
class TailwindRunner(
    @Value("\${tailwind.watcher:false}") private val run: String
){
    var tailwindThread: Thread? = null
    var keepRunning = AtomicBoolean(true)
    fun launchWatcher() {
        val builder = ProcessBuilder("npm", "run", "watch")
        builder.redirectErrorStream(true)
        val p = builder.start()
        val r = BufferedReader(InputStreamReader(p.inputStream))
        var line: String?
        while (keepRunning.get()) {
            line = r.readLine()
            if (line == null) {
                break
            }
            if(line.contains("Cannot find module")) {
                logger.error { "Error starting Tailwind. Perhaps 'npm install' is needed? $line" }
            }
            logger.info { line }
        }
    }

    @EventListener
    fun onStart(e: ApplicationReadyEvent) {
        if(run == "true") {
            tailwindThread = Thread { launchWatcher() }.apply {
                name = "tailwind-watcher"
                isDaemon = true
            }
            tailwindThread?.start()
        } else {
            logger.info { "tailwind.watcher is disabled" }
        }
    }

    @PreDestroy
    fun shutdown() {
        keepRunning.set(false)
    }

}