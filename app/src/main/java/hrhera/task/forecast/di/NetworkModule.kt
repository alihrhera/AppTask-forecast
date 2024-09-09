package hrhera.task.forecast.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hrhera.task.forecast.BuildConfig
import hrhera.task.forecast.utils.NetworkConstants
import hrhera.task.forecast.utils.NetworkConstants.CACHE_SIZE
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //Hilt Provide Network Cache
    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE)

    //
    /**
     * Provides a customized HttpLoggingInterceptor for logging network requests and responses.

     * The HttpLoggingInterceptor is configured to log the request and response body in debug builds,
     * while logging is disabled in release builds.
     *
     * @return The configured HttpLoggingInterceptor instance.
     *
     * @see HttpLoggingInterceptor
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }



    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor =
        ChuckerInterceptor.Builder(context = context)
            .build()


    //
    /*
           @Provides
       @Singleton
       fun provideStatusCodeInterceptor(pref: PreferenceHelper): StatusCodeInterceptor =
           StatusCodeInterceptor(pref)*/

    //
    /**
     * Provides a customized OkHttpClient instance for use in network operations.

     * The OkHttpClient instance is configured with various interceptors, timeouts, and a cache.
     *
     * @param cache The Cache instance for caching network responses.
     * @param headerInterceptor The custom HeaderInterceptor for adding headers to the HTTP request.
     * @param loggingInterceptor The HttpLoggingInterceptor for logging network requests and responses.
     * @param chuckerInterceptor ChuckerInterceptor to show response in notifications .
     * @return The configured OkHttpClient instance.
     *
     * @see Cache
     * @see HeaderInterceptor
     * @see StatusCodeInterceptor
     * @see HttpLoggingInterceptor
     * @see ChuckerInterceptor
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(NetworkConstants.timeOut, TimeUnit.SECONDS)
        readTimeout(NetworkConstants.timeOut, TimeUnit.SECONDS)
        writeTimeout(NetworkConstants.timeOut, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            addInterceptor(chuckerInterceptor)
        }
        addInterceptor(loggingInterceptor)
        cache(cache)
    }.build()

    /**
     * Provides a Gson instance with lenient configuration.
     * @return The configured Gson instance.
     * @see Gson
     */
    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    /**
     * Provides a GsonConverterFactory instance for use in Retrofit.
     * @param gson The Gson instance used for JSON conversion.
     * @return The configured GsonConverterFactory instance.
     *
     * @see GsonConverterFactory
     * @see Gson
     */
    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    /**
     * Provides a Retrofit instance for making network requests.
     * @param converterFactory The GsonConverterFactory instance for JSON conversion.
     * @param okHttpClient The OkHttpClient instance for handling HTTP requests.
     * @return The configured Retrofit instance.
     *
     * @see Retrofit
     * @see GsonConverterFactory
     * @see OkHttpClient
     */
    @Provides
    @Singleton @Named("forecast")
    fun provideForecastRetrofit(converterFactory: GsonConverterFactory, okHttpClient:
    OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstants.FORECAST_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton @Named("cities")
    fun provideCitiesRetrofit(converterFactory: GsonConverterFactory, okHttpClient: OkHttpClient):
            Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstants.CITIES_BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()


}
