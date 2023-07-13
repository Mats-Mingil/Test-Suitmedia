package com.rahmat.palindrome

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahmat.palindrome.data.MainRepository
import com.rahmat.palindrome.ui.secondScreen.SecondScreenViewModel
import com.rahmat.palindrome.ui.thirdScreen.ThirdScreenViewModel

class ViewModelFactory private constructor(private val repositoryMain: MainRepository) :
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondScreenViewModel::class.java)) {
            return SecondScreenViewModel(repositoryMain) as T
        }else if(modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)){
            return ThirdScreenViewModel(repositoryMain) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}