package nz.co.test.transactions.ui.transactions.detail

internal data class TransactionDetailViewStateBinding(
    val title: String,
    val transactionDate: String,
    val summary: String,
    val debit: String,
    val credit: String,
    val isCredit: Boolean,
    val gst: String
)