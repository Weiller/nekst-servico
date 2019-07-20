package br.com.nekstservico.repository

import br.com.nekstservico.domain.historicaloutputproduct.HistoricalOutputProduct
import org.springframework.data.jpa.repository.JpaRepository

interface HistoricalOutputProductRepository: JpaRepository<HistoricalOutputProduct, Long> {

    fun findByProductId(productId: Long): HistoricalOutputProduct
}