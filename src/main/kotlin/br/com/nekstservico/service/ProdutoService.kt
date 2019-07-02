package br.com.nekstservico.service

import br.com.nekstservico.dominio.Produto
import br.com.nekstservico.repository.ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProdutoService(val produtoRepository: ProdutoRepository) {

    fun testar (valor1: Long, valor2: Long): Long {
        produtoRepository.save(Produto(1, "we"))
        return valor1 + valor2
    }
}