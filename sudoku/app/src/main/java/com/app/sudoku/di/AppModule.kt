package com.app.sudoku.di

import com.app.sudoku.data.remote.api.SudokuApi
import com.app.sudoku.data.repository.SudokuRepositoryImpl
import com.app.sudoku.domain.repository.SudokuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-Api-Key", "wLVPN1zV08lJYF7uXqgyPw==zVwp6TlVcAO1NLUf")
                    .build()
                chain.proceed(request)
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideSudokuApi(retrofit: Retrofit): SudokuApi =
        retrofit.create(SudokuApi::class.java)

    @Provides
    @Singleton
    fun provideSudokuRepository(api: SudokuApi): SudokuRepository =
        SudokuRepositoryImpl(api)
}
