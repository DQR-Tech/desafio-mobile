package org.sabaini.desafiodqrtech.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.sabaini.desafiodqrtech.entities.Currency
import org.sabaini.desafiodqrtech.entities.ExchangeRate
import org.sabaini.desafiodqrtech.repositories.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(private val repository: CurrencyRepository) :
    ViewModel() {

    private var _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>>
        get() = _currencies

    private var _exchangeRates = MutableLiveData<List<ExchangeRate>>()

    private var _convertedValue = MutableLiveData<Double>()
    val convertedValue: LiveData<Double>
        get() = _convertedValue

    private var _inputError = MutableLiveData<String?>()
    val inputError: LiveData<String?>
        get() = _inputError

    init {
        _currencies = repository.getCurrencies().asLiveData() as MutableLiveData<List<Currency>>

        viewModelScope.launch {
            repository.getExchangeRates().collect { rates ->
                if (rates.isEmpty()) {
                    Log.d("error", "ExchangeRates is Empty")
                } else {
                    _exchangeRates.value = rates
                }
            }
        }
    }

    fun convert(origin: String, destination: String, value: String) {
        if (origin.isEmpty() || destination.isEmpty()) {
            _inputError.value = "Moedas não pode ser vazias"
            return
        }

        if (origin == destination) {
            _inputError.value = "Moedas não podem ser iguais"
            return
        }

        if (value.isEmpty()) {
            _inputError.value = "Valor não pode ser vazio"
            return
        } else {
            if (value.toFloat() == 0F || value.toFloat() < 0F) {
                _inputError.value = "Valor não pode ser 0 ou menor que 0"
                return
            }
        }

        when {
            origin == "USD" -> {
                val exchangeRate = _exchangeRates.value!!.find { it.pairTo == destination }
                _convertedValue.value = value.toDouble() * exchangeRate!!.conversionRate
            }
            destination == "USD" -> {
                val exchangeRate = _exchangeRates.value!!.find { it.pairTo == origin }
                _convertedValue.value = value.toDouble() / exchangeRate!!.conversionRate
            }
            else -> {
                val exchangeRate = _exchangeRates.value!!.find { it.pairTo == origin }
                val originInUsd = value.toDouble() / exchangeRate!!.conversionRate
                val exchangeRateDest = _exchangeRates.value!!.find { it.pairTo == destination }
                _convertedValue.value = originInUsd * exchangeRateDest!!.conversionRate
            }
        }
    }

    fun displayInputErrorComplete() {
        _inputError.value = null
    }
}