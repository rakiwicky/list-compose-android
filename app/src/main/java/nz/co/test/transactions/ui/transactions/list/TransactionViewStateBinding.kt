package nz.co.test.transactions.ui.transactions.list

internal data class TransactionViewStateBinding(
    val title: String,
    val transactions: List<TransactionListItem>,
    val contentVisible: Boolean,
    val errorVisible: Boolean,
    val loadingVisible: Boolean
)