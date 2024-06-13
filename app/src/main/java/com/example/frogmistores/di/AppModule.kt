@file:OptIn(ExperimentalPagingApi::class)

package com.example.frogmistores.di

import android.content.Context
import androidx.compose.ui.geometry.Rect
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.frogmistores.data.local.database.FrogmiStoreDatabase
import com.example.frogmistores.data.local.entities.StoreEntity
import com.example.frogmistores.data.paging.FrogmiStoresRemoteMediator
import com.example.frogmistores.data.remote.FrogmiStoresApi
import com.example.frogmistores.data.repository.StoreRepositoryImpl
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


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


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

    @Provides
    @Singleton
    fun provideStoreRepository(
        frogmiStoresApi: FrogmiStoresApi,
        storeDatabase: FrogmiStoreDatabase
    ): StoreRepository = StoreRepositoryImpl(frogmiStoresApi, storeDatabase)

}