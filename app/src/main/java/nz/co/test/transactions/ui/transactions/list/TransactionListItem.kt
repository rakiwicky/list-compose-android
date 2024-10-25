package nz.co.test.transactions.ui.transactions.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class TransactionListItem(
    val id: Int,
    val transactionDate: String,
    val summary: String,
    val debitPrice: String,
    val creditPrice: String,
    val isCredit: Boolean,
    val gstAmount: String
) : Parcelable

