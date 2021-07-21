package com.example.desafio.presentation.viewmodel.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.domain.usecase.remote.MoedaUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoedaViewModel(
    val moedaUsecase: MoedaUsecase
) : ViewModel() {

    private var mMoeda = MutableLiveData<MoedasDto>()

    val moeda:LiveData<MoedasDto>
        get() = mMoeda

    fun getAllMoedas(){
        CoroutineScope(Dispatchers.Main).launch {
            val moedas = withContext(Dispatchers.Default){
                moedaUsecase.invoke()
            }

            if(!moedas.moedas.isNullOrEmpty())
                mMoeda.value = moedas

        }
    }

}