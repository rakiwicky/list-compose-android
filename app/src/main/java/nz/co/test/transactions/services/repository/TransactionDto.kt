package nz.co.test.transactions.services.repository

import com.google.gson.annotations.SerializedName
import nz.co.test.base.util.Extensions.Companion.getFormattedDate
import nz.co.test.transactions.services.domain.Transaction
import java.math.BigDecimal

internal data class TransactionDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("transactionDate")
    val transactionDate: String,

    @SerializedName("summary")
    val summary: String,

    @SerializedName("debit")
    val debit: BigDecimal,

    @SerializedName("credit")
    val credit: BigDecimal
) {
    fun toTransaction(): Transaction {
        return Transaction(
            id = id,
            transactionDate = transactionDate.getFormattedDate("yyyy-MM-dd'T'HH:mm:ss"),
            summary = summary,
            debit = debit,
            credit = credit
        )
    }
}