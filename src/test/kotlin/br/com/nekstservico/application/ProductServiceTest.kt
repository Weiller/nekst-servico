package br.com.nekstservico.application

import br.com.nekstservico.config.TestSpecification
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.rest.CommandProduct
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceTest: TestSpecification() {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun should_be_save_product() {
        val product = productService.save(CommandProduct("Shampoo"))
        Assertions.assertNotNull(productRepository.findById(product.id!!))
    }
}