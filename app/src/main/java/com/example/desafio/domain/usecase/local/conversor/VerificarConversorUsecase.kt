package com.example.desafio.domain.usecase.local.conversor

import com.example.desafio.data.local.repository.ConversorLocalRepository

class VerificarConversorImpl(
    val repository: ConversorLocalRepository
): VerificarConversorUsecase {
    override suspend fun invoke(): Boolean = repository.verificarConversor()
}

interface VerificarConversorUsecase {
    suspend operator fun invoke(): Boolean
}