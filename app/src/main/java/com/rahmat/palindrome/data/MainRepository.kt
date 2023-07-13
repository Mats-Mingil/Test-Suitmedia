package com.rahmat.palindrome.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rahmat.palindrome.data.remote.response.DataItem
import com.rahmat.palindrome.data.remote.retrofit.ApiService

class MainRepository(private val apiService: ApiService) {

    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    fun savePreference(token: String, context: Context) {
        val settingPreference = Preference(context)
        settingPreference.setUser(token)
    }

    fun getPreference(context: Context): String? {
        val settingPreference = Preference(context)
        return settingPreference.getUser()
    }
}