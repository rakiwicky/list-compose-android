package nz.co.test.transactions.ui.transactions.list

import android.content.res.Resources
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import nz.co.test.transactions.R
import nz.co.test.transactions.ui.transactions.list.TransactionViewState.TargetState.*
import javax.inject.Inject

internal class TransactionViewState(
    resources: Resources
) {
    private val _binding = mutableStateOf(
        TransactionViewStateBinding(
            title = resources.getString(R.string.transactions_transaction_list_title),
            transactions = emptyList(),
            loadingVisible = false,
            contentVisible = false,
            errorVisible = false
        )
    )

    val binding: State<TransactionViewStateBinding> = _binding

    fun moveTo(targetState: TargetState) {
        val currentState = _binding.value

        _binding.value = when (targetState) {
            is Content -> currentState.copy(
                transactions = targetState.list,
                loadingVisible = false,
                contentVisible = true,
                errorVisible = false
            )
            Loading -> currentState.copy(
                loadingVisible = true,
                contentVisible = false,
                errorVisible = false
            )
            Error -> currentState.copy(
                loadingVisible = false,
                contentVisible = false,
                errorVisible = true
            )
        }
    }

    class Factory @Inject constructor(private val resources: Resources) {
        fun create(
        ) = TransactionViewState(resources)
    }

    sealed class TargetState {
        data class Content(val list: List<TransactionListItem>) : TargetState()
        object Loading : TargetState()
        object Error : TargetState()
    }
}