package com.rahmat.palindrome.ui.thirdScreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rahmat.palindrome.Injection
import com.rahmat.palindrome.data.MainRepository
import com.rahmat.palindrome.data.remote.response.DataItem

class ThirdScreenViewModel(private val mainRepository: MainRepository) : ViewModel(){

    private val token = MutableLiveData<String?>()

    val userData: LiveData<PagingData<DataItem>> = mainRepository.getUser().cachedIn(viewModelScope)

    fun setPrefernce(username: String, context: Context) =
        mainRepository.savePreference(username, context)


    fun getPreference(context: Context): LiveData<String?> {
        val tokenData = mainRepository.getPreference(context)
        token.value = tokenData
        return token
    }
}