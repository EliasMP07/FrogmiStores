@file:OptIn(ExperimentalPagingApi::class)

package com.example.frogmistores.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.frogmistores.data.local.database.FrogmiStoreDatabase
import com.example.frogmistores.data.remote.FrogmiStoresApi
import com.example.frogmistores.data.repository.StoreRepositoryImpl
import com.example.frogmistores.domain.GetAllStoresUseCase
import com.example.frogmistores.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Módulo de Dagger-Hilt para proveer dependencias a nivel de aplicación.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provee una instancia de OkHttpClient con configuraciones específicas.
     * @return Instancia de OkHttpClient.
     */
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Provee una instancia de FrogmiStoreDatabase.
     * @param context Contexto de la aplicación.
     * @return Instancia de FrogmiStoreDatabase.
     */
    @Provides
    @Singleton
    fun provideFrogmiStoreDatabase(
        @ApplicationContext context: Context
    ): FrogmiStoreDatabase {
        return Room.databaseBuilder(
            context,
            FrogmiStoreDatabase::class.java,
            "FrogmiStores.db"
        ).build()
    }

    /**
     * Provee una instancia de FrogmiStoresApi.
     * @param okHttpClient Instancia de OkHttpClient.
     * @return Instancia de FrogmiStoresApi.
     */
    @Provides
    @Singleton
    fun provideFrogmiStoreApi(
        okHttpClient: OkHttpClient
    ): FrogmiStoresApi {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(FrogmiStoresApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    /**
     * Provee una instancia de StoreRepository.
     * @param frogmiStoresApi Instancia de FrogmiStoresApi.
     * @param storeDatabase Instancia de FrogmiStoreDatabase.
     * @return Instancia de StoreRepository.
     */
    @Provides
    @Singleton
    fun provideStoreRepository(
        frogmiStoresApi: FrogmiStoresApi,
        storeDatabase: FrogmiStoreDatabase
    ): StoreRepository = StoreRepositoryImpl(frogmiStoresApi, storeDatabase)

    /**
     * Provee una instancia de GetAllStoresUseCase.
     * @param repository Instancia de StoreRepository.
     * @return Instancia de GetAllStoresUseCase.
     */
    @Provides
    @Singleton
    fun provideGetAllStoresUseCase(repository: StoreRepository): GetAllStoresUseCase = GetAllStoresUseCase(repository)

}