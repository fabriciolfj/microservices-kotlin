package com.github.fabriciolfj.productservice.domain.entity

import java.math.BigDecimal

data class Product(var id: Int = 0, var description: String = "", var price: BigDecimal = BigDecimal.ZERO)