package nz.co.test.transactions.services.repository

import nz.co.test.transactions.services.domain.Transaction
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

internal class TransactionsRepositoryImpl @Inject constructor(
    private val transactionsService: TransactionsService
) : TransactionsRepository {

    override suspend fun getTransactions(): List<Transaction> {
        return try {
            val response = transactionsService.retrieveTransactions()

            if (response.isSuccessful) {
                response.body()!!.map(TransactionDto::toTransaction)
            } else {
                throw IOException("Error getting details ${response.code()} ${response.message()}")
            }

        } catch (e: Exception) {
            throw IOException("Error getting details", e)
        }
    }
}