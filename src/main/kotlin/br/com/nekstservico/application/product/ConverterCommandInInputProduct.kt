package br.com.nekstservico.application.product

import br.com.nekstservico.domain.historicalinputproduct.HistoricalInputProduct
import br.com.nekstservico.domain.product.Product
import br.com.nekstservico.rest.CommandProduct
import java.time.LocalDate

object ConverterCommandInInputProduct {

    fun convert(product: Product, commandProduct: CommandProduct): HistoricalInputProduct {
        return HistoricalInputProduct(
                product = product,
                quantity = commandProduct.quantity,
                referenceDate = LocalDate.now()
        )
    }
}