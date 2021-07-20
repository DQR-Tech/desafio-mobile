package com.example.desafio.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.domain.usecase.GetMoeda
import com.example.desafio.domain.usecase.MoedaUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoedaViewModel(
    val usecase: GetMoeda
): ViewModel() {

    private var mMoeda = MutableLiveData<Map<String, String>>()

    val moeda:LiveData<Map<String, String>>
        get() = mMoeda

    fun getAllMoedas(){
        CoroutineScope(Dispatchers.Main).launch {
            val moedas = withContext(Dispatchers.Default){
                usecase.invoke().moedas
            }

            if(!moedas.isNullOrEmpty())
                mMoeda.value = moedas

        }
    }

    class viewModelFactory(val moeda: GetMoeda) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(GetMoeda::class.java)
                .newInstance(moeda)
        }
    }
}