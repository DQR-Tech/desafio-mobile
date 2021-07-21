package com.example.desafio.presentation.viewmodel.local.moeda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.domain.usecase.local.SelectUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectViewModel(
    private val selectUseCase: SelectUsecase
) : ViewModel() {

    private val mMoedas = MutableLiveData<MoedasDto>()

    val allMoedas: LiveData<MoedasDto>
        get() = mMoedas

    fun getAllMoedas() {
        CoroutineScope(Dispatchers.Main).launch {
            val moedasDto = withContext(Dispatchers.Default) {
                try {
                    selectUseCase.invoke()
                } catch (ex: Exception) {
                    Log.d("TAG", "getAllMoedas: $ex")
                    MoedasDto(false, "","",null)
                }
            }
            if(!moedasDto.moedas.isNullOrEmpty())
                mMoedas.value = moedasDto
        }
    }
}