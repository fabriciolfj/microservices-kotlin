package com.github.fabriciolfj.customerservice.api.exceptions

import java.lang.RuntimeException

class CustomerNotFoundException (message: String) : RuntimeException(message) {

}