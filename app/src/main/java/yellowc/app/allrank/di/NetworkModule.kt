package yellowc.app.allrank.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import yellowc.app.allrank.BuildConfig
import yellowc.app.allrank.data.remote.api.*
import yellowc.app.allrank.data.remote.datasourceimpl.JsoupServicesImpl
import yellowc.app.allrank.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    //TODO jsoup

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class BookStoreRetrofit

    @Provides
    @Singleton
    @BookStoreRetrofit
    fun provideBookStoreRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BOOK_STORE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideBookStoreApiService(
        @BookStoreRetrofit retrofit: Retrofit,
    ): BookStoreService {
        return retrofit.create(BookStoreService::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ForeignMusicRetrofit

    @Provides
    @Singleton
    @ForeignMusicRetrofit
    fun provideForeignMusicRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FOREIGN_MUSIC_API_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideForeignMusicApiService(
        @BookStoreRetrofit retrofit: Retrofit,
    ): ForeignService {
        return retrofit.create(ForeignService::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LibraryRetrofit

    @Provides
    @Singleton
    @LibraryRetrofit
    fun provideLibraryRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LIBRARY_API_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideLibraryApiService(
        @LibraryRetrofit retrofit: Retrofit,
    ): LibraryService {
        return retrofit.create(LibraryService::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TheaterRetrofit

    @Provides
    @Singleton
    @TheaterRetrofit
    fun provideTheaterRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MOVIE_BOXOFFICE_API_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideTheaterApiService(
        @TheaterRetrofit retrofit: Retrofit,
    ): TheaterService {
        return retrofit.create(TheaterService::class.java)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsRetrofit


    @Provides
    @Singleton
    @NewsRetrofit
    fun provideNewsRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NEWS_API_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(
        @NewsRetrofit retrofit: Retrofit,
    ): NewsService {
        return retrofit.create(NewsService::class.java)
    }


    @Provides
    @Singleton
    fun provideJsoupMenuService(): JsoupService {
        return JsoupServicesImpl()
    }

    //HttpClient

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    //Converter

    @Provides
    @Singleton
    fun providesConverterFactory() =
        json.asConverterFactory("application/json".toMediaType())

    //Interceptor

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }
}