package com.github.fabriciolfj.productservice.domain.service

import com.github.fabriciolfj.productservice.domain.entity.Product
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun getProduct(id: Int) : Mono<Product?>
    fun searchProducts(description: String): Flux<Product>
    fun create(productMono: Product) : Mono<Product>
}