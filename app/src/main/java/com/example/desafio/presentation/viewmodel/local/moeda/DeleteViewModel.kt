package com.example.desafio.presentation.viewmodel.local.moeda

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.usecase.local.DeleteUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteViewModel(
    private val deleteMovieUseCase: DeleteUsecase
) : ViewModel() {

    fun deleteMovie(){
        CoroutineScope(Dispatchers.Main).launch{
            try{
                deleteMovieUseCase.invoke()
            }catch (ex:Exception){
                Log.d("TAG", "insertMovie: $ex")
            }
        }
    }
}