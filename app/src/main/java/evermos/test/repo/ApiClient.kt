package evermos.test.repo

import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by siapaSAYA on 6/10/2020
 */

@Module
class ApiClient {

    companion object {

        val TAG = ApiClient::class.java.simpleName
        var retrofit: Retrofit? = null
        val REQUEST_TIMEOUT = 60
        var okHttpClient: OkHttpClient? = null

        @JvmStatic
        @Provides
        @Singleton
        fun GetClient(baseUrl : String): Retrofit? {

            if (okHttpClient == null)
                initOkHttp()

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit
        }


        @Singleton
        fun initOkHttp() {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(interceptor)

            okHttpClient = httpClient.build()
        }

        fun resetApiClient() {
            retrofit = null
            okHttpClient = null
        }
    }

}