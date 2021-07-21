package com.example.desafio.domain.usecase.local.conversor

import com.example.desafio.data.local.repository.ConversorLocalRepository
import com.example.desafio.domain.model.ConversorDto

class SelectConversorImple(
    val repository: ConversorLocalRepository
): SelectConversorUsecase {
    override suspend fun invoke() : ConversorDto = repository.selectConversor()
}

interface SelectConversorUsecase {
    suspend operator fun invoke() : ConversorDto
}
