package br.com.nekstservico.domain.historicaloutputproduct

import br.com.nekstservico.domain.product.Product
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "HISTORICAL_INPUT_PRODUCT", schema = "NEKST")
@SequenceGenerator(name = "SQ_HISTORICAL_INPUT_PRODUCT", sequenceName = "NEKST.SQ_HISTORICAL_INPUT_PRODUCT", allocationSize = 1, initialValue = 1)
data class HistoricalOutputProduct(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICAL_INPUT_PRODUCT")
                                   val id: Long? = null,

                                   @ManyToOne(fetch = FetchType.LAZY)
                                   @JoinColumn(name = "ID_PRODUCT")
                                   val product: Product,

                                   @Column(name = "QUANTITY")
                                   val quantity: Int,

                                   @Column(name = "REFERENCE_DATE")
                                   val referenceDate: LocalDate)