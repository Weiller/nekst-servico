package br.com.nekstservico.repository

import br.com.nekstservico.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface ProductRepository: JpaRepository<Product, Long>

