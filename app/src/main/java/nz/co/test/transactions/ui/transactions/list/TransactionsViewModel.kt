package nz.co.test.transactions.ui.transactions.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nz.co.test.base.BaseViewModel
import nz.co.test.transactions.services.repository.TransactionsRepository
import nz.co.test.transactions.ui.transactions.list.TransactionViewState.TargetState.*
import javax.inject.Inject

@HiltViewModel
internal class TransactionsViewModel @Inject constructor(
    private val transactionsRepository: TransactionsRepository,
    private val transactionsListMapper: TransactionsListMapper,
    viewStateFactory: TransactionViewState.Factory
) : BaseViewModel() {

    private val viewState = viewStateFactory.create()
    val binding = viewState.binding

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewState.moveTo(Loading)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = transactionsRepository.getTransactions()
                withContext(Dispatchers.Main) {
                    viewState.moveTo(Content(transactionsListMapper.transform(response)))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    viewState.moveTo(Error)
                }
            }
        }
    }
}