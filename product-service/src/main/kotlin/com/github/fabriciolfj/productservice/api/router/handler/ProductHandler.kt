package com.github.fabriciolfj.productservice.api.router.handler

import com.github.fabriciolfj.productservice.domain.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono

@Component
class ProductHandler {

    @Autowired
    lateinit var productService: ProductService

    fun getProduct(request: ServerRequest) : Mono<ServerResponse> {
        return productService.getProduct(request.pathVariable("id").toInt())
                .flatMap { ok().body(BodyInserters.fromValue(it!!)) }
                .switchIfEmpty(badRequest().build())
    }
}