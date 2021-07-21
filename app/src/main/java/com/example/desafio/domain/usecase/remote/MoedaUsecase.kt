package com.example.desafio.domain.usecase.remote

import android.util.Log
import com.example.desafio.data.remote.repository.MoedaRepository
import com.example.desafio.domain.model.MoedasDto

class GetMoeda(
    val moedaRepository: MoedaRepository
): MoedaUsecase {
    override suspend operator fun invoke(): MoedasDto = try{
        moedaRepository.getAllMoedas()
    }catch (ex:Exception){
        Log.d("TAG", "invoke: $ex")
        MoedasDto(false, "","",null)
    }

}

interface MoedaUsecase {
    suspend operator fun invoke():MoedasDto
}