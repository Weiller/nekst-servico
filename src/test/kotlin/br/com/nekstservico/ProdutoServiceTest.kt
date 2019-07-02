package br.com.nekstservico

import br.com.nekstservico.repository.ProdutoRepository
import br.com.nekstservico.service.ProdutoService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration


@SpringBootTest
@ContextConfiguration(classes = [EmptyDatabase::class])
class ProdutoServiceTest {

    @Autowired
    lateinit var produtoRepository: ProdutoRepository

    @Autowired
    lateinit var produtoService: ProdutoService

    @Test
    fun contextLoads() {
        val produto = produtoService.testar(1, 1)
        produtoRepository.findAll()

    }
}