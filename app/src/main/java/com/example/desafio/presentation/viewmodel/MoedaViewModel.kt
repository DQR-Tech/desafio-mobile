package com.example.desafio.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.usecase.MoedaUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoedaViewModel(
    val moedaUsecase: MoedaUsecase
) : ViewModel() {

    private var mMoeda = MutableLiveData<Map<String, String>>()

    val moeda:LiveData<Map<String, String>>
        get() = mMoeda

    fun getAllMoedas(){
        CoroutineScope(Dispatchers.Main).launch {
            val moedas = withContext(Dispatchers.Default){
                moedaUsecase.invoke().moedas
            }

            if(!moedas.isNullOrEmpty())
                mMoeda.value = moedas

        }
    }

}