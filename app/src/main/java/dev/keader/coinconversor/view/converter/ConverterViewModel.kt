package dev.keader.coinconversor.view.converter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keader.coinconversor.model.Event
import dev.keader.coinconversor.model.combineWith
import dev.keader.coinconversor.repository.CoinConverterRepository
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(repository: CoinConverterRepository): ViewModel() {

    val exchanges = repository.getAllExchanges()

    val convertValue = MutableLiveData("")
    private val convertValueValid = Transformations.map(convertValue) { value -> validateInput(value) }

    val originalCoin = MutableLiveData("")
    private val originalCoinValid = Transformations.map(originalCoin) { value -> validateInput(value) }

    val destinationCoin = MutableLiveData("")
    private val destinationCoinValid = Transformations.map(destinationCoin) { value -> validateInput(value) }

    val buttonEnabled = convertValueValid.combineWith(
        originalCoinValid,
        destinationCoinValid
    ) { a, b, c -> a == true && b == true && c == true }

    private val _result = MutableLiveData("")
    val result: LiveData<String>
        get() = _result

    private val _eventConvertClick = MutableLiveData<Event<Unit>>()
    val eventConvertClick: LiveData<Event<Unit>>
        get() = _eventConvertClick

    private val _eventListClick = MutableLiveData<Event<Unit>>()
    val eventListClick: LiveData<Event<Unit>>
        get() = _eventListClick

    private val _eventRetryClick = MutableLiveData<Event<Unit>>()
    val eventRetryClick: LiveData<Event<Unit>>
        get() = _eventRetryClick

    private fun validateInput(input: String) : Boolean {
        return input.isNotEmpty()
    }

    fun onConvertButtonClick() {
        _eventConvertClick.value = Event(Unit)
    }

    fun onListButtonClick() {
        _eventListClick.value = Event(Unit)
    }

    fun onRetryButtonClick() {
        _eventRetryClick.value = Event(Unit)
    }
}
