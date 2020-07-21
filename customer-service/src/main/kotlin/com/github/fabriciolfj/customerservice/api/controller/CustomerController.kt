package com.github.fabriciolfj.customerservice.api.controller

import com.github.fabriciolfj.customerservice.domain.entity.Customer
import com.github.fabriciolfj.customerservice.domain.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RequestMapping("/customers")
@RestController
class CustomerController {

    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping
    fun getCustomer() = ResponseEntity.ok().body(customerService.getCustomer())

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable("id") id : Int): ResponseEntity<Customer> {
        return ResponseEntity.ok().body(customerService.getCustomer(id))
    }

    @GetMapping("/filter")
    fun getCustomer(@RequestParam(required = false, defaultValue = "", name = "name") nameFiler: String) =
            ResponseEntity.ok().body(customerService.searchCustomers(nameFiler))

    @PostMapping
    fun create(@RequestBody customer: Customer): ResponseEntity<Void> {
        val result = customerService.create(customer)
        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(result.id).toUri();
        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Void> {
        customerService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody customer: Customer): ResponseEntity<Customer> {
        return ResponseEntity.ok().body(customerService.update(id, customer))
    }

}