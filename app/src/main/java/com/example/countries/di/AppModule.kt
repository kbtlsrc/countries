package com.example.countries.di
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    const val BASE_URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/"
    const val HEADER_API_KEY ="8c312de69fmsh1bd2f1547ca0b0dp1c12eejsn1e421f288daf"
    const val HEADER_RAPID_HOST = "wft-geo-db.p.rapidapi.com"
    private var retrofit: Retrofit = TODO()
    private val interceptor: HttpLoggingInterceptor

    @Provides
    @Singleton
    fun getApiClient(): Retrofit{
        if(retrofit == null){


            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor { chain ->
                    val urlWithParams = chain.request().url()
                        .newBuilder()
                        .build()
                    val request: Request = chain.request()
                        .newBuilder()
                        .addHeader("X-RapidAPI-Key", HEADER_API_KEY)
                        .url(urlWithParams)
                        .build();
                    chain.proceed(request);
                }.build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            }
        return retrofit

        }
    }




