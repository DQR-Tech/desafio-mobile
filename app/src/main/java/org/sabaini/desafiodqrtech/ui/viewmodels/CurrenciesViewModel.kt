package org.sabaini.desafiodqrtech.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sabaini.desafiodqrtech.entities.Currency
import org.sabaini.desafiodqrtech.repositories.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(repository: CurrencyRepository) :
    ViewModel() {

    private var _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>>
        get() = _currencies

    init {
        _currencies =
            repository.getCurrenciesFromDatabase().asLiveData() as MutableLiveData<List<Currency>>
    }
}