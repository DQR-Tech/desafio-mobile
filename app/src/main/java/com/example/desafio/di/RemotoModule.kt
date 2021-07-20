package com.example.desafio.di

import com.example.desafio.data.remote.network.ApiService
import com.example.desafio.data.remote.network.RetrofitInstance
import com.example.desafio.data.remote.repository.MoedaImpl
import com.example.desafio.data.remote.repository.MoedaRepository
import com.example.desafio.domain.usecase.GetMoeda
import com.example.desafio.domain.usecase.MoedaUsecase
import com.example.desafio.presentation.viewmodel.MoedaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val RemotoModule = module {
//    viewModel { MoedaViewModel() }

//    single { RetrofitInstance().retrofitInstance() as ApiService }
//    single { MoedaImpl(apiService = get()) as MoedaRepository }
//    single { GetMoeda(moedaRepository = get()) as MoedaUsecase }

}