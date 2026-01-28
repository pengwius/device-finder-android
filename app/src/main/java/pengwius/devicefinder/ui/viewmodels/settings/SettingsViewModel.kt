package pengwius.devicefinder.ui.viewmodels.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pengwius.devicefinder.data.settings.SettingsDataStore
import pengwius.devicefinder.data.models.settings.ThemeMode
import kotlinx.coroutines.flow.StateFlow
import pengwius.devicefinder.data.models.settings.Language

class SettingsViewModel(private val dataStore: SettingsDataStore) : ViewModel() {
    val monetEnabled = dataStore.monetEnabledFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, true)

    fun setMonetEnabled(enabled: Boolean) {
        viewModelScope.launch {
            dataStore.setMonetEnabled(enabled)
        }
    }

    val themeMode: StateFlow<ThemeMode> =
        dataStore.themeModeFlow()
            .stateIn(viewModelScope, SharingStarted.Eagerly, ThemeMode.AUTO)

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            dataStore.setThemeMode(mode)
        }
    }

    val language: StateFlow<Language> =
        dataStore.languageFlow()
            .stateIn(viewModelScope, SharingStarted.Eagerly, Language.ENGLISH)

    fun setLanguage(lang: Language) {
        viewModelScope.launch {
            dataStore.setLanguage(lang)
        }
    }
}
