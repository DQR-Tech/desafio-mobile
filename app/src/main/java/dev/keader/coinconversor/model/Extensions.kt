package dev.keader.coinconversor.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, K, Z, R> LiveData<T>.combineWith(
    liveData1: LiveData<K>,
    liveData2: LiveData<Z>,
    block: (T?, K?, Z?) -> R): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        result.value = block(this.value, liveData1.value, liveData2.value)
    }
    result.addSource(liveData1) {
        result.value = block(this.value, liveData1.value, liveData2.value)
    }
    result.addSource(liveData2) {
        result.value = block(this.value, liveData1.value, liveData2.value)
    }
    return result
}
