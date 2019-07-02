package br.com.nekstservico.dominio

import javax.persistence.*

@Entity
@Table(name = "produto", schema = "NEKST")
@SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "NEKST.SQ_PRODUTO", allocationSize = 1, initialValue = 1)
class Produto(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "SQ_PRODUTO") val id: Long, val nome: String) {
}