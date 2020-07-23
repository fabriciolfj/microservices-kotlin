package com.github.fabriciolfj.productservice.api.router.handler


import com.github.fabriciolfj.productservice.domain.entity.Product
import com.github.fabriciolfj.productservice.domain.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.net.URI

@Component
class ProductHandler {

    @Autowired
    lateinit var productService: ProductService

    fun getProduct(request: ServerRequest) : Mono<ServerResponse> {
        return productService.getProduct(request.pathVariable("id").toInt())
                .flatMap { ok().body(BodyInserters.fromValue(it!!)) }
                .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())
    }

    fun getProductName(request: ServerRequest) : Mono<ServerResponse> {
        return productService.searchProducts(request.queryParam("name").orElse(""))
                .flatMap { ok().body(BodyInserters.fromValue(it)) }.toMono()
    }

    fun createProduct(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Product::class.java)
                .flatMap { productService.create(it) }
                .flatMap { ServerResponse.created(URI.create("/functional/${it.id}")).body(BodyInserters.fromValue(it)) }
    }
}