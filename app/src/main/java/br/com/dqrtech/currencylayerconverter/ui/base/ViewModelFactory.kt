package br.com.dqrtech.currencylayerconverter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dqrtech.currencylayerconverter.data.api.CurrencyLayerHelper
import br.com.dqrtech.currencylayerconverter.data.repository.CurrencyLayerRepository
import br.com.dqrtech.currencylayerconverter.ui.main.viewmodel.CurrenciesViewModel
import br.com.dqrtech.currencylayerconverter.ui.main.viewmodel.MainViewModel

class ViewModelFactory (private val currencyLayerHelper: CurrencyLayerHelper) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(CurrencyLayerRepository(currencyLayerHelper)) as T
        }
        if (modelClass.isAssignableFrom(CurrenciesViewModel::class.java)) {
            return CurrenciesViewModel(CurrencyLayerRepository(currencyLayerHelper)) as T
        }
        throw IllegalArgumentException("Unknow class name")
    }

}