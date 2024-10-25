package nz.co.test.transactions.ui.transactions.list

import nz.co.test.transactions.services.domain.Transaction
import java.math.BigDecimal
import nz.co.test.base.util.Extensions.Companion.getFormattedDateStr
import nz.co.test.base.util.Extensions.Companion.getPercentage
import nz.co.test.base.util.Extensions.Companion.getPrice

internal class TransactionsListMapper {

    fun transform(transactions: List<Transaction>): List<TransactionListItem> {
        return transactions.sortedBy { it.transactionDate }.map { createListItem(it) }
    }

    private fun createListItem(transaction: Transaction): TransactionListItem {
        return TransactionListItem(
            id = transaction.id,
            summary = transaction.summary,
            debitPrice = transaction.debit.getPrice(),
            creditPrice = transaction.credit.getPrice(),
            isCredit = isCredit(transaction),
            transactionDate = transaction.transactionDate.getFormattedDateStr("dd-MM-yyyy HH:mm"),
            gstAmount = if (isCredit(transaction)) {
                transaction.credit.getPercentage(15).getPrice()
            } else {
                transaction.debit.getPercentage(15).getPrice()
            }
        )
    }

    private fun isCredit(transaction: Transaction): Boolean {
        return transaction.credit.compareTo(BigDecimal.ZERO) != 0 && transaction.debit.compareTo(BigDecimal.ZERO) == 0
    }
}