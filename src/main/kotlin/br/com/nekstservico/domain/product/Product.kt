package br.com.nekstservico.domain.product

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "PRODUCT", schema = "NEKST")
@SequenceGenerator(name = "SQ_PRODUCT", sequenceName = "NEKST.SQ_PRODUCT", allocationSize = 1, initialValue = 1)
data class Product(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUCT")
                   val id: Long? = null,

                   @Column(name = "NAME")
                   val name: String,

                   @Column(name = "DESCRIPTION")
                   val description: String,

                   @Column(name = "VALUE")
                   val value: BigDecimal,

                   @Column(name = "STATUS")
                   val status: ProductStatusEnum)