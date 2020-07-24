package com.github.fabriciolfj.productservice.api.exceptions

import java.lang.RuntimeException

class ProductNotFoundException(var msg: String): RuntimeException(msg) {
}