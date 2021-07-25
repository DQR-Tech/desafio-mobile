package dev.keader.coinconversor.model

import android.app.Activity
import android.os.Handler
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

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

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> {
    return Transformations.distinctUntilChanged(this)
}

fun Handler.removeOldEvents() {
    removeCallbacksAndMessages(null)
}

fun Activity.windowInsetsControllerCompat(view: View): WindowInsetsControllerCompat? {
    return WindowCompat.getInsetsController(window, view)
}

val View.windowInsetsControllerCompat: WindowInsetsControllerCompat?
    get() = ViewCompat.getWindowInsetsController(this)

fun View.closeKeyboard() {
    windowInsetsControllerCompat?.hide(WindowInsetsCompat.Type.ime())
}

fun View.openKeyboard() {
    windowInsetsControllerCompat?.show(WindowInsetsCompat.Type.ime())
}
