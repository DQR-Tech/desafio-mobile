package br.com.dqrtech.currencylayerconverter.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.dqrtech.currencylayerconverter.data.repository.CurrencyLayerRepository
import br.com.dqrtech.currencylayerconverter.utils.Resource
import kotlinx.coroutines.Dispatchers

class CurrenciesViewModel(private val currencyLayerRepository: CurrencyLayerRepository) :ViewModel() {

    fun getCurrencyList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = currencyLayerRepository.getCurrencyList()
            if(!data.success) throw Exception("" + data.error!!.code + " - " + data.error.info)
            emit(Resource.success(data = data))
        } catch (exeption: Exception) {
            emit(Resource.error(data = null, message = exeption.message ?: "Error Ocurred!"))
        }
    }
}