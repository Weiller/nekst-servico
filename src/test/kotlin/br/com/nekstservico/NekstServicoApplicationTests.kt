package br.com.nekstservico

import br.com.nekstservico.infra.EmptyDatabase
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [EmptyDatabase::class])
class NekstServicoApplicationTests {

    @Test
    fun contextLoads() {
        main(arrayOf())
    }
}
