package com.example.desafio.presentation.viewmodel.local.conversor

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.usecase.local.conversor.DeleteConversorUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteConversorViewModel(
    private val deleteUseCase: DeleteConversorUsecase
) : ViewModel() {

    fun deleteMovie(){
        CoroutineScope(Dispatchers.Main).launch{
            try{
                deleteUseCase.invoke()
            }catch (ex:Exception){
                Log.d("TAG", "insertMovie: $ex")
            }
        }
    }
}