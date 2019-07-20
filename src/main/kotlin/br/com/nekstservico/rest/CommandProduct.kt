package br.com.nekstservico.rest

import br.com.nekstservico.domain.product.ProductOperationEnum
import br.com.nekstservico.domain.product.ProductStatusEnum
import java.math.BigDecimal

class CommandProduct(val name: String,
                     val description: String,
                     val value: BigDecimal,
                     val quantity: Int,
                     val operation: ProductOperationEnum,
                     val status: ProductStatusEnum)
