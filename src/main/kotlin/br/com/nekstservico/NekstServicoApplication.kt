package br.com.nekstservico

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class NekstServicoApplication

fun main(args: Array<String>) {
	runApplication<NekstServicoApplication>(*args)
}
