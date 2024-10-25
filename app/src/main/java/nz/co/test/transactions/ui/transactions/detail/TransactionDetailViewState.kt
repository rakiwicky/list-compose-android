package nz.co.test.transactions.ui.transactions.detail

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import nz.co.test.transactions.R
import nz.co.test.transactions.ui.transactions.list.TransactionListItem
import javax.inject.Inject

internal class TransactionDetailViewState(
    resources: Resources,
    transactionListItem: TransactionListItem
) {
    private val _binding = mutableStateOf(
        TransactionDetailViewStateBinding(
            title = resources.getString(R.string.transactions_transaction_detail_title),
            transactionDate = transactionListItem.transactionDate,
            summary = transactionListItem.summary,
            debit = transactionListItem.debitPrice,
            credit = transactionListItem.creditPrice,
            isCredit = transactionListItem.isCredit,
            gst = transactionListItem.gstAmount
        )
    )

    val binding: State<TransactionDetailViewStateBinding> = _binding

    class Factory @Inject constructor(private val resources: Resources) {
        fun create(
            transactionListItem: TransactionListItem
        ) = TransactionDetailViewState(resources, transactionListItem)
    }
}