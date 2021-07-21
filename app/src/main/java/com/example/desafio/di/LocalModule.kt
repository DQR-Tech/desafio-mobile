package com.example.desafio.di

import com.example.desafio.data.local.MoedaDatabase
import com.example.desafio.data.local.dao.MoedaDao
import com.example.desafio.data.local.repository.MoedaDataSource
import com.example.desafio.data.local.repository.MoedaLocalRepository
import com.example.desafio.domain.usecase.local.InsertImpl
import com.example.desafio.domain.usecase.local.InsertUsecase
import com.example.desafio.presentation.viewmodel.local.InsertViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val LocalModule = module {
    viewModel { InsertViewModel(insertImpl = get()) }

    single { MoedaDatabase.getInstance(androidContext()).moedaDao as MoedaDao}

    single { MoedaDataSource(moedaDao = get()) as MoedaLocalRepository }
    single { InsertImpl(repository = get()) as InsertUsecase }
}