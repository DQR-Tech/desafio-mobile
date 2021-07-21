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
    viewModel { InsertViewModel(insertImpl = get()) }
    viewModel { VerificarViewModel(verificarUseCase = get()) }
    viewModel { DeleteViewModel(deleteMovieUseCase = get()) }
    viewModel { SelectViewModel(selectUseCase = get()) }

    single { MoedaDatabase.getInstance(androidContext()).moedaDao as MoedaDao}

    single { MoedaDataSource(moedaDao = get()) as MoedaLocalRepository }
    single { InsertImpl(repository = get()) as InsertUsecase }
    single { VerifiacarImpl(repository = get()) as VerificarUsecase }
    single { DeteteImple(repository = get()) as DeleteUsecase }
    single { SelectImple(repository = get()) as SelectUsecase }
}