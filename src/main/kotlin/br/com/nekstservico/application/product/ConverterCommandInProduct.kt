package br.com.nekstservico.application.product

import br.com.nekstservico.domain.product.Product
import br.com.nekstservico.rest.CommandProduct

object ConverterCommandInProduct {

    fun convert(commandProduct: CommandProduct): Product {
        return Product(name = commandProduct.name,
                description = commandProduct.description,
                value = commandProduct.value,
                status = commandProduct.status)
    }
}