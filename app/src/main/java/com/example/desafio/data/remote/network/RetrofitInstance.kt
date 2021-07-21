package com.example.desafio.data.remote.network

import com.example.desafio.utilis.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{
        private fun getRetrofit() : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

        fun retrofitInstance() = getRetrofit().create(ApiService::class.java)
    }
}