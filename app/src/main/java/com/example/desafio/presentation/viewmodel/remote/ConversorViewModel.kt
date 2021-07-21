package com.example.desafio.presentation.viewmodel.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.model.ConversorDto
import com.example.desafio.domain.usecase.ConversorUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversorViewModel(
    val conversorUsecase: ConversorUsecase
) : ViewModel() {

    private var mMoeda = MutableLiveData<ConversorDto>()

    val moeda: LiveData<ConversorDto>
        get() = mMoeda

    fun getSearchMoedas(){
        CoroutineScope(Dispatchers.Main).launch {
            val moedas = withContext(Dispatchers.Default){
                conversorUsecase.invoke()
            }

            if(!moedas.moedas.isNullOrEmpty())
                mMoeda.value = moedas

        }
    }

}