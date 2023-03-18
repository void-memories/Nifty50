package com.voidmemories.nifty50.di

import com.voidmemories.nifty50.core.Constants
import com.voidmemories.nifty50.data.datasources.api.Nifty50Api
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesNifty50Api(retrofit: Retrofit): Nifty50Api {
        return retrofit.create(Nifty50Api::class.java)
    }
}
