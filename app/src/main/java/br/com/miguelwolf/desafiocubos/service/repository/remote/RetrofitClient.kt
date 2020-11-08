package br.com.miguelwolf.desafiocubos.service.repository.remote

import br.com.miguelwolf.desafiocubos.service.constants.MovieConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(): Retrofit {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .build()
                    return chain.proceed(request)
                }
            })

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(MovieConstants.API.BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }

}