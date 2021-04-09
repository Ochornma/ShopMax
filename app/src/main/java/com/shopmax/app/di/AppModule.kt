package com.shopmax.app.di

import com.shopmax.app.network.Api
import com.shopmax.app.repo.DefaultRepository
import com.shopmax.app.repo.MainRepositrory
import com.shopmax.app.util.Constants.BASE_URL
import com.shopmax.app.util.DispasherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePixabayApi(): Api {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(api: Api): DefaultRepository =  MainRepositrory(api)


    @Singleton
    @Provides
    fun provideDispatchers(): DispasherProvider = object : DispasherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}