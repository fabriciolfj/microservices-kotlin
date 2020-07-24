package com.github.fabriciolfj.productservice.domain.repository

import com.github.fabriciolfj.productservice.domain.entity.Product
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ProductRepository: ReactiveMongoRepository<Product, String> {

    fun findByDescriptionIgnoreCase(description: String): Flux<Product>
}