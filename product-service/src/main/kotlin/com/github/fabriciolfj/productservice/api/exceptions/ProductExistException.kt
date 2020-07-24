package com.github.fabriciolfj.productservice.api.exceptions

import java.lang.RuntimeException

class ProductExistException(override val message: String) : RuntimeException(message)

