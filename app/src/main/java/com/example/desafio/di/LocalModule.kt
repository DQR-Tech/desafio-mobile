package com.example.desafio.di

import com.example.desafio.data.local.AppDatabase
import com.example.desafio.data.local.dao.ConversorDao
import com.example.desafio.data.local.dao.MoedaDao
import com.example.desafio.data.local.repository.ConversorDataSource
import com.example.desafio.data.local.repository.ConversorLocalRepository
import com.example.desafio.data.local.repository.MoedaDataSource
import com.example.desafio.data.local.repository.MoedaLocalRepository
import com.example.desafio.domain.usecase.local.*
import com.example.desafio.domain.usecase.local.conversor.*
import com.example.desafio.presentation.viewmodel.local.moeda.DeleteViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.InsertViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.SelectViewModel
import com.example.desafio.presentation.viewmodel.local.moeda.VerificarViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.DeleteConversorViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.InsertConversorViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.SelectConversorViewModel
import com.example.desafio.presentation.viewmodel.local.conversor.VerificarConversorViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val LocalModule = module {

    //viewModels local
    viewModel { InsertViewModel(insertImpl = get()) }
    viewModel { InsertConversorViewModel(insertImpl = get()) }

    viewModel { DeleteViewModel(deleteMovieUseCase = get()) }
    viewModel { DeleteConversorViewModel(deleteUseCase = get()) }

    viewModel { VerificarViewModel(verificarUseCase = get()) }
    viewModel { VerificarConversorViewModel(verificarUseCase = get()) }

    viewModel { SelectViewModel(selectUseCase = get()) }
    viewModel { SelectConversorViewModel(selectUseCase = get()) }

    //pegando os DAOs
    single { AppDatabase.getInstance(androidContext()).moedaDao as MoedaDao}
    single { AppDatabase.getInstance(androidContext()).conversorDao as ConversorDao}

    //repositorys
    single { MoedaDataSource(moedaDao = get()) as MoedaLocalRepository }
    single { ConversorDataSource(conversorDao = get()) as ConversorLocalRepository }

    //inserts
    single { InsertImpl(repository = get()) as InsertUsecase }
    single { InsertConversorImpl(repository = get()) as InsertConversorUsecase }

    //deletes
    single { DeleteImpl(repository = get()) as DeleteUsecase }
    single { DeleteConversorImpl(repository = get()) as DeleteConversorUsecase }

    //selects
    single { SelectImple(repository = get()) as SelectUsecase }
    single { SelectConversorImple(repository = get()) as SelectConversorUsecase }

    //verificar
    single { VerifiacarImpl(repository = get()) as VerificarUsecase }
    single { VerificarConversorImpl(repository = get()) as VerificarConversorUsecase }
}