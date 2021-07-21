package com.example.desafio.domain.usecase.local

import com.example.desafio.data.local.repository.MoedaLocalRepository

class DeleteImpl(
    val repository: MoedaLocalRepository
):DeleteUsecase{
    override suspend fun invoke() = repository.deleteMoedas()
}

interface DeleteUsecase {
    suspend operator fun invoke()
}