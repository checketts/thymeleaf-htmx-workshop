package com.github.checketts.palindromes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

//@EnableConfigurationProperties(PalindromeConfig::class)
@SpringBootApplication
class HtmxExampleApplication{

	@Bean
	fun externalSecurity(http: HttpSecurity): SecurityFilterChain {
		return http
//				.userDetailsService
				.requestMatchers()
				.antMatchers("/**")
				.and().authorizeRequests()
				.anyRequest().permitAll()
				.and().csrf().disable()
				.build()
	}


}

fun main(args: Array<String>) {
	runApplication<HtmxExampleApplication>(*args)
}
