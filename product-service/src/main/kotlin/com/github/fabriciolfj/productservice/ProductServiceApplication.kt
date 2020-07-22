package com.github.fabriciolfj.productservice

import com.github.fabriciolfj.productservice.domain.entity.Product
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class ProductServiceApplication {

	val initialProducts = arrayOf(
			Product(1,"Arroz", BigDecimal(9.99).setScale(2, RoundingMode.HALF_DOWN)),
			Product(2,"Farinha", BigDecimal(10.88).setScale(2, RoundingMode.HALF_DOWN)),
			Product(3,"Arroz carreteiro", BigDecimal(15.87).setScale(2, RoundingMode.HALF_DOWN)),
			Product(4,"Farinha temperada", BigDecimal(19.99).setScale(2, RoundingMode.HALF_DOWN))
	)

	@Bean
	fun customers() = ConcurrentHashMap<Int, Product>(initialProducts.associateBy(Product::id))
}

fun main(args: Array<String>) {
	runApplication<ProductServiceApplication>(*args)
}
