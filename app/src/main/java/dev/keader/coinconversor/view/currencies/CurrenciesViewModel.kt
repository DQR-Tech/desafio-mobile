package dev.keader.coinconversor.view.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keader.coinconversor.database.model.Currency
import dev.keader.coinconversor.repository.CoinConverterRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: CoinConverterRepository
) : ViewModel() {

    val search = MutableLiveData("")

    private val _currenciesList = MutableLiveData<List<Currency>>(emptyList())
    val currenciesList: LiveData<List<Currency>>
        get() = _currenciesList

    fun getAllElementsByName() = viewModelScope.launch {
        _currenciesList.value = repository.getAllCurrenciesOrderByName()
    }

    fun getAllElementsByCode() = viewModelScope.launch {
        _currenciesList.value = repository.getAllCurrenciesOrderByCode()
    }

    fun getSearchElementsByName(search: String) = viewModelScope.launch {
        _currenciesList.value = repository.getCurrenciesBySearchOrderByName(search)
    }

    fun getSearchElementsByCode(search: String) = viewModelScope.launch {
        _currenciesList.value = repository.getCurrenciesBySearchOrderByCode(search)
    }
}
