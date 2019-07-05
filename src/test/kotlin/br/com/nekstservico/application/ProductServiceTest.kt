package br.com.nekstservico.application

import br.com.nekstservico.config.TestSpecification
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.application.ProductService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceTest: TestSpecification() {

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