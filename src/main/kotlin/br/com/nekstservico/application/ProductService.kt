package br.com.nekstservico.application

import br.com.nekstservico.domain.Product
import br.com.nekstservico.repository.ProductRepository
import br.com.nekstservico.rest.CommandProduct
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun save(commandProduct: CommandProduct) {
        productRepository.save(Product(nome = commandProduct.nome))
    }

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }
}