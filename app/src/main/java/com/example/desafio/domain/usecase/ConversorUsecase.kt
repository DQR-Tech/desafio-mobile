package com.example.desafio.domain.usecase

import android.util.Log
import com.example.desafio.data.remote.repository.MoedaRepository
import com.example.desafio.domain.model.ConversorDto

class GetConversor(
    val moedaRepository: MoedaRepository
) : ConversorUsecase{
    override suspend fun invoke(codigoMoedas:String): ConversorDto = try {
        moedaRepository.getSearchMoeda(codigoMoedas)
    }catch (ex:Exception){
        Log.d("TAG", "invoke: $ex")
        ConversorDto(false, "","",0, "", null)
    }

}

interface ConversorUsecase {
    suspend operator fun invoke(codigoMoedas:String): ConversorDto
}