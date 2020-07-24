package com.github.fabriciolfj.productservice.domain.service.impl

import com.github.fabriciolfj.productservice.api.exceptions.ProductExistException
import com.github.fabriciolfj.productservice.api.exceptions.ProductNotFoundException
import com.github.fabriciolfj.productservice.domain.entity.Product
import com.github.fabriciolfj.productservice.domain.repository.ProductRepository
import com.github.fabriciolfj.productservice.domain.service.ProductService
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Service
class ProductServiceImpl : ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    override fun findAll(): Flux<Product> {
        return productRepository.findAll()
                .switchIfEmpty(Flux.empty())
    }

    override fun getProduct(id: String): Mono<Product?> {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error<Product>(ProductNotFoundException("Product not found: $id")))
    }

    override fun searchProducts(description: String): Flux<Product> {
        return productRepository.findByDescriptionIgnoreCase(description)
                .switchIfEmpty(Flux.empty())
    }

    override fun create(productMono: Product): Mono<Product> {
        return productRepository.findByDescriptionIgnoreCase(productMono.description)
                .flatMap { Mono.error<Product>(ProductExistException("Product ${it.id} already exist")) }.toMono()
                .switchIfEmpty(productRepository.save(productMono))
    }

    override fun delete(id: String): Mono<Void> {
        return productRepository.deleteById(id)
    }

    override fun update(productMono: Product, id: String): Mono<Product> {
        return productRepository.findById(id)
                .flatMap {
                    BeanUtils.copyProperties(productMono, it, "id")
                    productRepository.save(it)
                }.switchIfEmpty(Mono.error<Product>(ProductNotFoundException("Product not found: $id")))
    }
}