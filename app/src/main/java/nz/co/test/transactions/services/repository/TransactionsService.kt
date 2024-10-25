package nz.co.test.transactions.services.repository

import retrofit2.Response
import retrofit2.http.GET

internal interface TransactionsService {

    @GET("test-data.json")
    suspend fun retrieveTransactions(): Response<List<TransactionDto>>
}

