package com.example.desafio.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.usecase.ConversorUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversorViewModel(
    val conversorUsecase: ConversorUsecase
) : ViewModel() {

    private var mMoeda = MutableLiveData<Map<String, Double>>()

    val moeda: LiveData<Map<String, Double>>
        get() = mMoeda

    fun getSearchMoedas(codigoMoedas:String){
        CoroutineScope(Dispatchers.Main).launch {
            val moedas = withContext(Dispatchers.Default){
                conversorUsecase.invoke(codigoMoedas).moeda
            }

            if(!moedas.isNullOrEmpty())
                mMoeda.value = moedas

        }
    }

}