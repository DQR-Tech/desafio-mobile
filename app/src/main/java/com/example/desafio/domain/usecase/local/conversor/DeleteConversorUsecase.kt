package com.example.desafio.domain.usecase.local.conversor

import com.example.desafio.data.local.repository.ConversorLocalRepository

class DeleteConversorImpl(
    val repository: ConversorLocalRepository
): DeleteConversorUsecase {
    override suspend fun invoke() = repository.deleteConversor()
}

interface DeleteConversorUsecase {
    suspend operator fun invoke()
}