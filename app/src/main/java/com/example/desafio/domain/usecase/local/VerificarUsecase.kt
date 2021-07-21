package com.example.desafio.domain.usecase.local

import com.example.desafio.data.local.repository.MoedaLocalRepository

class VerifiacarImpl(
    val repository: MoedaLocalRepository
):VerificarUsecase{
    override suspend fun invoke(): Boolean = repository.verificarMoeda()
}

interface VerificarUsecase {
    suspend operator fun invoke(): Boolean
}