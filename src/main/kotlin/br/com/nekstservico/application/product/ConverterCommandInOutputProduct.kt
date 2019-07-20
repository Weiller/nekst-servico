package br.com.nekstservico.application.product

import br.com.nekstservico.domain.historicaloutputproduct.HistoricalOutputProduct
import br.com.nekstservico.domain.product.Product
import br.com.nekstservico.rest.CommandProduct
import java.time.LocalDate

object ConverterCommandInOutputProduct {

    fun convert(product: Product, commandProduct: CommandProduct): HistoricalOutputProduct {
        return HistoricalOutputProduct(
                product = product,
                quantity = commandProduct.quantity,
                referenceDate = LocalDate.now()
        )
    }
}