package com.example.applicationestudo.domain.usecases

import com.example.applicationestudo.data.models.Live
import com.example.applicationestudo.data.repositories.MainRepository
import retrofit2.Call

class GetLive(private  val mainRepository: MainRepository) : GeLiveUseCase {
    override fun get(): Call<List<Live>> {
        val mainRepository = mainRepository.getAllLives()
       return  mainRepository;
    }
}


interface GeLiveUseCase {
    fun get(): Call<List<Live>>
}