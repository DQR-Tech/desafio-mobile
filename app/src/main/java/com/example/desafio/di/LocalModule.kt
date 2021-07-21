package com.example.desafio.di

import com.example.desafio.data.local.MoedaDatabase
import com.example.desafio.data.local.dao.MoedaDao
import com.example.desafio.data.local.repository.MoedaDataSource
import com.example.desafio.data.local.repository.MoedaLocalRepository
import com.example.desafio.domain.usecase.local.*
import com.example.desafio.presentation.viewmodel.local.DeleteViewModel
import com.example.desafio.presentation.viewmodel.local.InsertViewModel
import com.example.desafio.presentation.viewmodel.local.SelectViewModel
import com.example.desafio.presentation.viewmodel.local.VerificarViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val LocalModule = module {
    //pegando o moedaDao e repository
    single { MoedaDatabase.getInstance(androidContext()).moedaDao as MoedaDao}
    single { MoedaDataSource(moedaDao = get()) as MoedaLocalRepository }

    //insert moeda
    viewModel { InsertViewModel(insertImpl = get()) }
    single { InsertImpl(repository = get()) as InsertUsecase }

    //delete moeda
    viewModel { DeleteViewModel(deleteMovieUseCase = get()) }
    single { DeleteImpl(repository = get()) as DeleteUsecase }

    //verificar
    viewModel { VerificarViewModel(verificarUseCase = get()) }
    single { VerifiacarImpl(repository = get()) as VerificarUsecase }

    //select
    viewModel { SelectViewModel(selectUseCase = get()) }
    single { SelectImple(repository = get()) as SelectUsecase }
}