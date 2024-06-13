package com.example.frogmistores.di

import android.content.Context
import androidx.room.Room
import com.example.frogmistores.data.local.database.FrogmiStoreDatabase
import com.example.frogmistores.data.remote.FrogmiStoresApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFrogmiStoreDatabase(
        @ApplicationContext context: Context
    ): FrogmiStoreDatabase{
        return Room.databaseBuilder(
            context,
            FrogmiStoreDatabase::class.java,
            "FrogmiStores.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFrogmiStoreApi(): FrogmiStoresApi{
        return Retrofit.Builder()
            .baseUrl(FrogmiStoresApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi{
        return Moshi.Builder().build()
    }

}