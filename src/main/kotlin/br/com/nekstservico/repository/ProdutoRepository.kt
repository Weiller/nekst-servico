package br.com.nekstservico.repository

import br.com.nekstservico.dominio.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional
interface ProdutoRepository: JpaRepository<Produto, Long> {}

