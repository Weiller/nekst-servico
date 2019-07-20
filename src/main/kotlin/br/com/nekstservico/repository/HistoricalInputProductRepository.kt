package br.com.nekstservico.repository

import br.com.nekstservico.domain.historicalinputproduct.HistoricalInputProduct
import org.springframework.data.jpa.repository.JpaRepository

interface HistoricalInputProductRepository: JpaRepository<HistoricalInputProduct, Long> {

    fun findByProductId(productId: Long): HistoricalInputProduct
}