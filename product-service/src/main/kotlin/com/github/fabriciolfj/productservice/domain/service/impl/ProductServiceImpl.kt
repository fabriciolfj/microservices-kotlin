package com.github.fabriciolfj.productservice.domain.service.impl

import com.github.fabriciolfj.productservice.domain.entity.Product
import com.github.fabriciolfj.productservice.domain.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class ProductServiceImpl: ProductService {

    @Autowired
    lateinit var initialProducts : ConcurrentHashMap<Int, Product>


    override fun getProduct(id: Int): Mono<Product?> {
        return Flux.fromIterable(initialProducts.map { it.value }.toList())
                .filter { p -> p.id == id }
                .toMono()
    }

    override fun searchProducts(description: String): Flux<Product> {
        return Flux.fromIterable(initialProducts.map { it.value }.toList())
                .filter { p -> p.description.contains (description, ignoreCase = true) }
                .toFlux()
    }

    override fun create(productMono: Product): Mono<Product> {
        return Mono.just(productMono)
                .flatMap {
                    initialProducts[it.id] = it
                    Mono.just(productMono)
                }
    }

}