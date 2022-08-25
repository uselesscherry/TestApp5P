package com.cherry.testapp5p.di

import androidx.room.Room
import com.cherry.testapp5p.data.UserRepositoryImpl
import com.cherry.testapp5p.data.local.UserDatabase
import com.cherry.testapp5p.data.remote.LoginApi
import com.cherry.testapp5p.data.remote.TrustedManager
import com.cherry.testapp5p.domain.UserRepository
import com.cherry.testapp5p.ui.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<LoginApi> {
        Retrofit.Builder()
            .baseUrl(LoginApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(TrustedManager.getUnsafeOkHttpClient()) //uses custom client to bypass certificate check
            .build()
            .create(LoginApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            UserDatabase.DB_NAME
        ).build()
    }

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    viewModel { LoginViewModel(get()) }

}