package com.example.desafio.domain.usecase.local

import com.example.desafio.data.local.repository.MoedaLocalRepository
import com.example.desafio.domain.model.MoedasDto

class SelectImple(
    val repository: MoedaLocalRepository
):SelectUsecase{
    override suspend fun invoke() : MoedasDto = repository.selectMoeda()
}

interface SelectUsecase {
    suspend operator fun invoke() : MoedasDto
}