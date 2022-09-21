package com.zhytel.myworknote.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(
    private val id: Int,
    private val application: Application
): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
//            return DetailViewModel(id, application) as T
//        }
//        throw RuntimeException("Unknown view model class $modelClass")
//    }

}