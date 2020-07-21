package com.github.fabriciolfj.customerservice.domain.service

import com.github.fabriciolfj.customerservice.domain.entity.Customer

interface CustomerService {
    fun getCustomer(): List<Customer>
    fun getCustomer(id: Int) : Customer?
    fun create(customer: Customer) : Customer
    fun delete(id: Int)
    fun update(id: Int, customer: Customer) : Customer?
    fun searchCustomers(name: String): List<Customer>
}