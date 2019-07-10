package br.com.nekstservico.rest

import br.com.nekstservico.application.ProductService
import br.com.nekstservico.domain.Product
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/product")
class ProductRest(private val productService: ProductService) {

    @GetMapping
    fun consultar(): ResponseEntity<List<Product>> {
        return ResponseEntity(productService.findAll(), HttpStatus.OK)
    }

    @PostMapping
    fun save(@Valid @RequestBody commandProduct: CommandProduct): ResponseEntity<Product> {
       return ResponseEntity(productService.save(commandProduct), HttpStatus.OK)
    }
}