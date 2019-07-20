package br.com.nekstservico.application.product

import br.com.nekstservico.domain.historicalinputproduct.HistoricalInputProduct
import br.com.nekstservico.domain.historicaloutputproduct.HistoricalOutputProduct
import br.com.nekstservico.domain.product.Product
import br.com.nekstservico.domain.product.ProductOperationEnum
import br.com.nekstservico.repository.HistoricalInputProductRepository
import br.com.nekstservico.repository.HistoricalOutputProductRepository
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.rest.CommandProduct
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository,
                     val historicalInputProductRepository: HistoricalInputProductRepository,
                     val historicalOutputProductRepository: HistoricalOutputProductRepository) {

    fun save(commandProduct: CommandProduct): Product {
        val product = productRepository.save(ConverterCommandInProduct.convert(commandProduct))

        if (commandProduct.operation == ProductOperationEnum.INPUT) createInputHistorical(product, commandProduct) else createOutputHistorical(product, commandProduct)

        return product
    }

    fun createInputHistorical(product: Product, commandProduct: CommandProduct): HistoricalInputProduct {
        return historicalInputProductRepository.save(ConverterCommandInInputProduct.convert(product, commandProduct))
    }

    fun createOutputHistorical(product: Product, commandProduct: CommandProduct): HistoricalOutputProduct {
       return historicalOutputProductRepository.save(ConverterCommandInOutputProduct.convert(product, commandProduct))
    }

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}