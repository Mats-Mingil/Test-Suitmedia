package com.rahmat.palindrome.ui.secondScreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahmat.palindrome.data.MainRepository

class SecondScreenViewModel(private val repositoryMain: MainRepository) : ViewModel() {

    private val username = MutableLiveData<String?>()

    fun getPreference(context: Context): LiveData<String?> {
        val userName = repositoryMain.getPreference(context)
        username.value = userName
        return username
    }

}