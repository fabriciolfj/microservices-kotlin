package com.github.fabriciolfj.productservice.domain.service

import com.github.fabriciolfj.productservice.domain.entity.Product
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun findAll(): Flux<Product>
    fun getProduct(id: String) : Mono<Product?>
    fun searchProducts(description: String): Flux<Product>
    fun create(productMono: Product) : Mono<Product>
    fun delete(id: String) : Mono<Void>
    fun update(productMono: Product, id: String) : Mono<Product>
}