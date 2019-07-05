package br.com.nekstservico.application

import br.com.nekstservico.domain.Product
import br.com.nekstservico.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    fun testar (valor1: Long, valor2: Long): Long {
        productRepository.save(Product(1, "we"))
        return valor1 + valor2
    }
}