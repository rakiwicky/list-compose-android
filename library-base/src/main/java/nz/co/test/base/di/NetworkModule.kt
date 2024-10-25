package nz.co.test.base.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.co.test.base.network.RestServiceBuilder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun restServiceBuilder(): RestServiceBuilder {
        return RestServiceBuilder(
            GsonConverterFactory.create()
        )
    }

    @Provides
    @Named("TransactionServiceBaseUrl")
    @Singleton
    fun provideTransactionBaseUrl(): String {
        return "https://gist.githubusercontent.com/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/"
    }
}