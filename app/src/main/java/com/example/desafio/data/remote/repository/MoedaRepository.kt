package com.example.desafio.data.remote.repository

import android.util.Log
import com.example.desafio.data.remote.network.ApiService
import com.example.desafio.domain.model.ConversorDto
import com.example.desafio.domain.model.MoedasDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoedaImpl(
    val apiService: ApiService
): MoedaRepository {
    override suspend fun getAllMoedas() : MoedasDto {
        return withContext(Dispatchers.Default){
            val response = apiService.getAllMoeda()

            if(response.isSuccessful)
                response.body()!!
            else{
                Log.d("TAG", "Error: ${response.errorBody()} ")
                Log.d("TAG", "Code: ${response.code()} ")
                MoedasDto(false, "", "", null)
            }
        }
    }

    override suspend fun getSearchMoeda(codigoMoedas:String) : ConversorDto {
        return withContext(Dispatchers.Default){
            val response = apiService.getSearchMoeda(codigoMoeda = codigoMoedas)

            if(response.isSuccessful)
                response.body()!!
            else{
                Log.d("TAG", "Error: ${response.errorBody()} ")
                Log.d("TAG", "Code: ${response.code()} ")
                ConversorDto(false, "","",0, "", null)
            }
        }
    }

}

interface MoedaRepository {
    suspend fun getAllMoedas() : MoedasDto
    suspend fun getSearchMoeda(codigoMoedas:String) : ConversorDto
}