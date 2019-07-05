package br.com.nekstservico

import br.com.nekstservico.infra.EmptyDatabase
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.service.ProductService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration


@SpringBootTest
@ContextConfiguration(classes = [EmptyDatabase::class])
class ProductServiceTest {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var productService: ProductService

    @Test
    fun contextLoads() {
        productService.testar(1, 1)
        productRepository.findAll()
    }
}