package com.github.fabriciolfj.productservice.api.router

import com.github.fabriciolfj.productservice.api.router.handler.ProductHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class ProductRouter {

    @Autowired
    lateinit var productHandler: ProductHandler

    @Bean
    fun productRoutes() = router {
        "/functional".nest {
            GET("/{id}") {
                productHandler.getProduct(it)
            }
            POST("/", productHandler::createProduct)
        }
        "/productsfunc".nest {
            GET("/") {
                productHandler.getProductName(it)
            }
        }
    }
}