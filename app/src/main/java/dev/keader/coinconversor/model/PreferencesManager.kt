package dev.keader.coinconversor.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.keader.coinconversor.model.PreferencesManager.PreferencesKeys.DATE
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_settings")

    private val dataStore = context.dataStore
    val dateStringFlow = dataStore.data.map {
        it[DATE] ?: ""
    }

    suspend fun saveLastUpdateDate(date: String) {
        dataStore.edit {
            it[DATE] = date
        }
    }

    private object PreferencesKeys {
        val DATE = stringPreferencesKey("last_update")
    }
}
