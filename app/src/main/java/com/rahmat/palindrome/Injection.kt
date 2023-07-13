package com.rahmat.palindrome

import android.content.Context
import com.rahmat.palindrome.data.MainRepository
import com.rahmat.palindrome.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): MainRepository{
        val apiService = ApiConfig.getApiService()
        return  MainRepository(apiService)
    }
}