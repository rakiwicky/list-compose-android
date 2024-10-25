package nz.co.test.base.network

import retrofit2.Converter
import retrofit2.Retrofit

class RestServiceBuilder(
    private val converterFactory: Converter.Factory
) {

    fun <T> create(kClass: Class<T>, _baseUrl: String): T {
        val baseUrl = if (_baseUrl.endsWith("/")) _baseUrl else "$_baseUrl/"

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
            .create(kClass)
    }
}