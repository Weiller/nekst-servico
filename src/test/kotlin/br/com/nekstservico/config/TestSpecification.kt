package br.com.nekstservico.config

import br.com.nekstservico.infra.EmbeddedDatabase
import br.com.nekstservico.infra.LocalDataBase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [EmbeddedDatabase::class, LocalDataBase::class])
class TestSpecification