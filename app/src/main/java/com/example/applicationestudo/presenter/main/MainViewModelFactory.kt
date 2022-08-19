package com.example.applicationestudo.presenter.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.applicationestudo.data.repositories.MainRepository
import com.example.applicationestudo.domain.usecases.GeLiveUseCase

class MainViewModelFactory constructor(private val geLiveUseCase: GeLiveUseCase ) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.geLiveUseCase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}