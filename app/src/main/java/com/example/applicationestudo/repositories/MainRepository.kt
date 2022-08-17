package com.example.applicationestudo.repositories

import com.example.applicationestudo.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){
    fun getAllLives() = retrofitService.getAllLives();
}