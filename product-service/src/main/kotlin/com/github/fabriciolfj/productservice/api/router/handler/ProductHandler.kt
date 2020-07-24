package com.github.fabriciolfj.productservice.api.router.handler


import com.github.fabriciolfj.productservice.api.exceptions.dto.ErrorResponse
import com.github.fabriciolfj.productservice.domain.entity.Product
import com.github.fabriciolfj.productservice.domain.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
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

    fun findAll(request: ServerRequest) : Mono<ServerResponse> {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product::class.java)
    }

    fun getProduct(request: ServerRequest) : Mono<ServerResponse> {
        return productService.getProduct(request.pathVariable("id"))
                .flatMap { ok().body(BodyInserters.fromValue(it!!)) }
                .onErrorResume { badRequest().body(BodyInserters.fromValue(ErrorResponse("error get product", it.message ?: "error")))}
    }

    fun getProductName(request: ServerRequest) : Mono<ServerResponse> {
        return productService.searchProducts(request.queryParam("name").orElse(""))
                .flatMap { ok().body(BodyInserters.fromValue(it)) }.toMono()
    }

    fun createProduct(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Product::class.java)
                .flatMap { productService.create(it) }
                .flatMap { created(URI.create("/functional/${it.id}")).body(BodyInserters.fromValue(it)) }
                .onErrorResume(Exception::class.java) {
                    badRequest().body(BodyInserters.fromValue(ErrorResponse("error creating product", it.message ?: "error")))
                }
    }

    fun deleteById(request: ServerRequest) : Mono<ServerResponse> {
        return productService.delete(request.pathVariable("id"))
                .flatMap { noContent().build() }
    }

    fun update(request: ServerRequest) : Mono<ServerResponse> {
        return request.bodyToMono(Product::class.java)
                .flatMap { productService.update(it, request.pathVariable("id")) }
                .flatMap { accepted().body(BodyInserters.fromValue(it)) }
                .onErrorResume { badRequest().body(BodyInserters.fromValue(ErrorResponse("error update", it.message?: "error"))) }
    }
}