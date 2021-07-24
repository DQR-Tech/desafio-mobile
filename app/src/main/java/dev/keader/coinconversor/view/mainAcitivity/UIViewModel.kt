package dev.keader.coinconversor.view.mainAcitivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.keader.coinconversor.model.Event
import dev.keader.coinconversor.repository.CoinConverterRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class UIViewModel @Inject constructor(
    private val repository: CoinConverterRepository
) : ViewModel() {

    private val _hasLoadInProgress = MutableLiveData(false)
    val hasLoadInProgress: LiveData<Boolean>
        get() = _hasLoadInProgress

    private val _onUpdateDataResponse = MutableLiveData<Event<Boolean>>()
    val onUpdateDataResponse: LiveData<Event<Boolean>>
        get() = _onUpdateDataResponse

    fun updateData() {
        Timber.d("Updating data...")
        _hasLoadInProgress.value = true
        viewModelScope.launch {
            val success = repository.updatedDataFromNetwork()
            _onUpdateDataResponse.value = Event(success)
            _hasLoadInProgress.value = false
        }
    }
}
