package br.com.nekstservico

import br.com.nekstservico.infra.WebConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [WebConfig::class])
class NekstServicoApplication

fun main(args: Array<String>) {
	runApplication<NekstServicoApplication>(*args)
}
