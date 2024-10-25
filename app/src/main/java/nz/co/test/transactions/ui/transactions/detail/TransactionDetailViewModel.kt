package nz.co.test.transactions.ui.transactions.detail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import nz.co.test.transactions.ui.transactions.list.TransactionListItem
import javax.inject.Inject

@HiltViewModel
internal class TransactionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    viewStateFactory: TransactionDetailViewState.Factory
) : nz.co.test.base.BaseViewModel() {

    private val transactionListItem = savedStateHandle.get<TransactionListItem>("transaction")

    private val viewState = viewStateFactory.create(transactionListItem!!)
    val binding = viewState.binding
}