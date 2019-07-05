package br.com.nekstservico.config

import br.com.nekstservico.infra.EmptyDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [EmptyDatabase::class])
class TestSpecification