package br.com.nekstservico.rest

import br.com.nekstservico.application.product.ProductService
import br.com.nekstservico.domain.product.Product
import br.com.nekstservico.domain.product.ProductOperationEnum
import br.com.nekstservico.domain.product.ProductStatusEnum
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductRestTest {

    lateinit var mockMvc: MockMvc

    @InjectMocks
    lateinit var productRest: ProductRest

    @Mock
    lateinit var productService: ProductService

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private fun <T> anyObject(): T {
        return Mockito.any<T>()
    }

    @BeforeAll
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productRest).build()

        Mockito.`when`(productService.findAll()).thenReturn(listOf(Product(2, "a", "a", BigDecimal(123), ProductStatusEnum.ACTIVE)))
        Mockito.`when`(productService.save(anyObject())).thenReturn(Product(2, "a", "a", BigDecimal(123), ProductStatusEnum.ACTIVE))
    }

    @Test
    fun should_get_products() {
        this.mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id':2,'name':'a' }]"))
    }

    @Test
    fun should_save_products() {
        val json = objectMapper.writeValueAsString(CommandProduct("a", "a", BigDecimal(123), 1, ProductOperationEnum.INPUT, ProductStatusEnum.ACTIVE))

        this.mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().isOk)
                .andExpect(content().json("{'id':2, 'name': 'a', 'description':'a','value':123,'status':'ACTIVE'}"))
    }
}