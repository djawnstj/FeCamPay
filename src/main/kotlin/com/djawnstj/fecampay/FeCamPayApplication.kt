package com.djawnstj.fecampay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class FeCamPayApplication

fun main(args: Array<String>) {
    runApplication<FeCamPayApplication>(*args)
}
