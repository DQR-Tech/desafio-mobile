package com.example.desafio.presentation.viewmodel.local.conversor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.usecase.local.conversor.VerificarConversorUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VerificarConversorViewModel(
    private val verificarUseCase: VerificarConversorUsecase
) : ViewModel() {

    private var mVerificado = MutableLiveData<Boolean>()

    val verificado: LiveData<Boolean>
        get() = mVerificado

    fun verificar() = CoroutineScope(Dispatchers.Main).launch {
        val valor = withContext(Dispatchers.Default){
            try{
                verificarUseCase.invoke()
            }catch (ex:Exception){
                Log.d("TAG", "verificar: $ex")
                false
            }
        }
        mVerificado.value = valor
    }
}