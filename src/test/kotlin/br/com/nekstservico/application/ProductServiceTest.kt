package br.com.nekstservico.application

import br.com.nekstservico.config.TestSpecification
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.application.ProductService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

class ProductServiceTest: TestSpecification() {

    @Autowired
    lateinit var productService: ProductService

    @Test
    fun should_be_returned_all_products() {
        val products = productService.findAll()
        Assertions.assertEquals(products.size,  0)
    }
}