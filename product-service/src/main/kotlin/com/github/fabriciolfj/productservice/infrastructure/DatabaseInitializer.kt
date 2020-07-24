package com.github.fabriciolfj.productservice.infrastructure

import com.github.fabriciolfj.productservice.domain.entity.Product
import com.github.fabriciolfj.productservice.domain.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.math.BigDecimal
import java.math.RoundingMode
import javax.annotation.PostConstruct

@Configuration
class DatabaseInitializer {

    @Autowired
    lateinit var productRepository: ProductRepository

    companion object {
        val initialProduct = listOf<Product>(
                Product(null, "Arroz", BigDecimal(19.89).setScale(2, RoundingMode.HALF_DOWN)),
                Product(null, "Feijão", BigDecimal(16.43).setScale(2, RoundingMode.HALF_DOWN)),
                Product(null, "Farinha", BigDecimal(5.77).setScale(2, RoundingMode.HALF_DOWN))
        )
    }

    @PostConstruct
    fun initData() {
        productRepository.deleteAll().subscribe()
        Flux.fromIterable(initialProduct)
                .flatMap { it -> create(it) }
                .subscribe()
    }

    fun create(product: Product) = productRepository.save(product)
}