package br.com.nekstservico.application.product

import br.com.nekstservico.config.TestSpecification
import br.com.nekstservico.domain.product.ProductOperationEnum
import br.com.nekstservico.domain.product.ProductStatusEnum
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.rest.CommandProduct
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal

class ProductServiceTest: TestSpecification() {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun should_be_save_product() {
        val product = productService.save(CommandProduct("a", "a", BigDecimal(123), 1, ProductOperationEnum.INPUT, ProductStatusEnum.ACTIVE))
        Assertions.assertNotNull(productRepository.findById(product.id!!))
    }
}