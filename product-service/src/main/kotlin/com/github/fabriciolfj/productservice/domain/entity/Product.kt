package com.github.fabriciolfj.productservice.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "products")
data class Product(@Id var id: String?, var description: String = "", var price: BigDecimal = BigDecimal.ZERO)