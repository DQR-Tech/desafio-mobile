package com.example.desafio.di

import com.example.desafio.data.remote.network.ApiService
import com.example.desafio.data.remote.network.RetrofitInstance
import com.example.desafio.data.remote.repository.MoedaImpl
import com.example.desafio.data.remote.repository.MoedaRepository
import com.example.desafio.domain.usecase.ConversorUsecase
import com.example.desafio.domain.usecase.GetConversor
import com.example.desafio.domain.usecase.remote.GetMoeda
import com.example.desafio.domain.usecase.remote.MoedaUsecase
import com.example.desafio.presentation.viewmodel.remote.ConversorViewModel
import com.example.desafio.presentation.viewmodel.remote.MoedaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val RemotoModule = module {
    // pegando a ApiService e o repository
    single { RetrofitInstance.retrofitInstance() as ApiService }
    single { MoedaImpl(apiService = get()) as MoedaRepository }

    //moeda
    viewModel { MoedaViewModel(moedaUsecase = get()) }
    single { GetMoeda(moedaRepository = get()) as MoedaUsecase }

    //conversor
    viewModel { ConversorViewModel(conversorUsecase = get()) }
    single { GetConversor(moedaRepository = get()) as ConversorUsecase}

}