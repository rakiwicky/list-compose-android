package nz.co.test.transactions.services.domain

import java.math.BigDecimal
import java.time.LocalDateTime

internal data class Transaction(
    val id: Int,
    val transactionDate: LocalDateTime,
    val summary: String,
    val debit: BigDecimal,
    val credit: BigDecimal
)