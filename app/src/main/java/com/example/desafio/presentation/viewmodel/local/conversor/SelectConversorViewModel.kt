package com.example.desafio.presentation.viewmodel.local.conversor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.model.ConversorDto
import com.example.desafio.domain.usecase.local.conversor.SelectConversorUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectConversorViewModel(
    private val selectUseCase: SelectConversorUsecase
) : ViewModel() {

    private val mConversor = MutableLiveData<ConversorDto>()

    val allMoedas: LiveData<ConversorDto>
        get() = mConversor

    fun getAllMoedas() {
        CoroutineScope(Dispatchers.Main).launch {
            val conversorDto = withContext(Dispatchers.Default) {
                try {
                    selectUseCase.invoke()
                } catch (ex: Exception) {
                    Log.d("TAG", "getAllMoedas: $ex")
                    ConversorDto(false,"","",0,"", null)
                }
            }
            if(!conversorDto.moedas.isNullOrEmpty())
                mConversor.value = conversorDto
        }
    }
}