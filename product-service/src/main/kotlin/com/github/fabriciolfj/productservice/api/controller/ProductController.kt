package com.github.fabriciolfj.productservice.api.controller

import com.github.fabriciolfj.productservice.domain.entity.Product
import com.github.fabriciolfj.productservice.domain.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/products")
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: String) : Mono<ResponseEntity<Product?>> {
        return productService.getProduct(id)
                .map { ResponseEntity.ok().body(it) }
                .defaultIfEmpty(ResponseEntity.badRequest().build<Product>())
    }

    @GetMapping("/description/{description}")
    fun getProducts(@PathVariable description: String) = productService.searchProducts(description)

    @PostMapping
    fun create(@RequestBody product: Product) : ResponseEntity<Mono<*>> {
        return ResponseEntity(productService.create(product), HttpStatus.CREATED)
    }
}