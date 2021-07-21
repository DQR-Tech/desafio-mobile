package com.example.desafio.presentation.viewmodel.local.moeda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.model.MoedasDto
import com.example.desafio.domain.usecase.local.InsertUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsertViewModel(
    val insertImpl: InsertUsecase
) : ViewModel() {

    private var mCadastrado = MutableLiveData<Boolean>()

    val cadastrado: LiveData<Boolean>
        get() = mCadastrado

    fun insertMoeda(moedasDto:MoedasDto){
        CoroutineScope(Dispatchers.Main).launch {
            val id = withContext(Dispatchers.Default){
                try {
                    insertImpl.invoke(moedasDto)
                }catch (ex:Exception){
                    Log.d("TAG", "insertMoeda: $ex")
                    0
                }
            }

            if(id > 0)
                mCadastrado.value = true
        }
    }

}