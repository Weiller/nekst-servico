package br.com.nekstservico.application.product

import br.com.nekstservico.config.TestSpecification
import br.com.nekstservico.domain.product.ProductOperationEnum
import br.com.nekstservico.domain.product.ProductStatusEnum
import br.com.nekstservico.repository.HistoricalInputProductRepository
import br.com.nekstservico.repository.HistoricalOutputProductRepository
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

    @Autowired
    lateinit var historicalInputProductRepository: HistoricalInputProductRepository

    @Autowired
    lateinit var historicalOutputProductRepository: HistoricalOutputProductRepository

    @Test
    fun should_save_product_with_input_operation() {
        val product = productService.save(CommandProduct("Shampoo", "descrição shampoo",
                BigDecimal(123), 1, ProductOperationEnum.INPUT, ProductStatusEnum.ACTIVE))

        val historicalInputProduct = historicalInputProductRepository.findByProductId(product.id!!)

        Assertions.assertNotNull(productRepository.findById(product.id!!))
        Assertions.assertNotNull(historicalInputProduct)
        Assertions.assertEquals(historicalInputProduct.quantity, 1)
        Assertions.assertEquals(historicalInputProduct.product.id, product.id!!)
    }

    @Test
    fun should_save_product_with_output_operation() {
        val product = productService.save(CommandProduct("Condicionador", "descrição condicionador",
                BigDecimal(123), 1, ProductOperationEnum.OUTPUT, ProductStatusEnum.ACTIVE))

        val historicalOutputProduct = historicalOutputProductRepository.findByProductId(product.id!!)

        Assertions.assertNotNull(productRepository.findById(product.id!!))
        Assertions.assertNotNull(historicalOutputProduct)
        Assertions.assertEquals(historicalOutputProduct.quantity, 1)
    }
}