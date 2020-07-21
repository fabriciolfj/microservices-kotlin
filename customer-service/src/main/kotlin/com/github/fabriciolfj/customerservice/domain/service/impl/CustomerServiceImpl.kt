package com.github.fabriciolfj.customerservice.domain.service.impl

import com.github.fabriciolfj.customerservice.api.exceptions.CustomerNotFoundException
import com.github.fabriciolfj.customerservice.domain.entity.Customer
import com.github.fabriciolfj.customerservice.domain.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl: CustomerService {

    @Autowired
    lateinit var customers : ConcurrentHashMap<Int, Customer>

    override fun getCustomer(): List<Customer> {
        return customers.map { c -> c.value }.toList()
    }

    override fun getCustomer(id: Int): Customer? {
        return customers
                .filter {it.value.id == id}
                .map { c -> c.value }
                .firstOrNull() ?: throw CustomerNotFoundException("Customer not found. Id: $id")
    }

    override fun create(customer: Customer): Customer {
        customers[customer.id] = customer
        return customers[customer.id]!!
    }

    override fun delete(id: Int) {
        customers.remove(id)
    }

    override fun update(id: Int, customer: Customer) : Customer? {
        if (customers[id] == null) {
            return null;
        }

        customers.remove(id)
        customers[customer.id] = customer
        return customers[customer.id]
    }

    override fun searchCustomers(name: String): List<Customer> {
        return customers.filter { c -> c.value.name.contains(name) }.map { c -> c.value }.toList()
    }

}