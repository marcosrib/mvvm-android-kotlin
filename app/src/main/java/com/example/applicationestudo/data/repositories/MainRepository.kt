package com.example.applicationestudo.data.repositories

import com.example.applicationestudo.data.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){
    fun getAllLives() = retrofitService.getAllLives();
}