package nz.co.test.transactions

import android.content.Context
import android.content.res.Resources
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nz.co.test.base.network.RestServiceBuilder
import nz.co.test.transactions.services.repository.TransactionsRepository
import nz.co.test.transactions.services.repository.TransactionsRepositoryImpl
import nz.co.test.transactions.services.repository.TransactionsService
import nz.co.test.transactions.ui.transactions.list.TransactionsListMapper
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionModule {

    @Binds
    internal abstract fun bindRepository(impl: TransactionsRepositoryImpl): TransactionsRepository

    companion object {

        @Provides
        @Singleton
        internal fun provideTransactionService(
            restServiceBuilder: RestServiceBuilder,
            @Named("TransactionServiceBaseUrl") baseUrl: String
        ): TransactionsService {
            return restServiceBuilder.create(TransactionsService::class.java, baseUrl)
        }

        @Provides
        @Singleton
        internal fun provideListMapper(): TransactionsListMapper {
            return TransactionsListMapper()
        }

        @Provides
        @Singleton
        fun provideResources(
            @ApplicationContext context: Context
        ): Resources {
            return context.resources
        }
    }
}