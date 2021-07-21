package com.example.desafio.data.remote.network

import com.example.desafio.domain.model.ConversorDto
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.utilis.USER_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/list")
    suspend fun getAllMoeda(
        @Query("access_key") chaveUsuario:String = USER_KEY
    ) : Response<MoedasDto>

    @GET("api/live")
    suspend fun getSearchMoeda(
        @Query("access_key") chaveUsuario:String = USER_KEY,
        @Query("currencies") codigoMoeda:String
    ) : Response<ConversorDto>
}