package com.github.fabriciolfj.customerservice

import com.github.fabriciolfj.customerservice.domain.entity.Customer
import com.github.fabriciolfj.customerservice.domain.entity.Telephone
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class CustomerServiceApplication {

	companion object {
		val initialCustomers = arrayOf(
				Customer(1, "Kotlin", Telephone(15, 99883321)),
				Customer(2, "Microservice"),
				Customer(3, "Spring", Telephone(36, 529987896))
		)
	}

	@Bean
	fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}



fun main(args: Array<String>) {
	runApplication<CustomerServiceApplication>(*args)
}
