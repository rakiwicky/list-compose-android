package nz.co.test.transactions.services.repository

import nz.co.test.transactions.services.domain.Transaction

internal interface TransactionsRepository {
    suspend fun getTransactions(): List<Transaction>
}