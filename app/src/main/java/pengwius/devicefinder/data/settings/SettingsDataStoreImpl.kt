package pengwius.devicefinder.data.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pengwius.devicefinder.data.models.settings.ThemeMode
import pengwius.devicefinder.data.models.settings.Language

class SettingsDataStoreImpl(private val dataStore: DataStore<Preferences>) : SettingsDataStore {
    private val MONET_KEY = booleanPreferencesKey("monet_enabled")
    private val THEME_KEY = stringPreferencesKey("theme_mode")
    private val LANGUAGE_KEY = stringPreferencesKey("language")

    override fun monetEnabledFlow(): Flow<Boolean> =
        dataStore.data.map { prefs -> prefs[MONET_KEY] ?: true }

    override suspend fun setMonetEnabled(enabled: Boolean) {
        dataStore.edit { prefs ->
            prefs[MONET_KEY] = enabled
        }
    }

    override fun themeModeFlow(): Flow<ThemeMode> =
        dataStore.data.map { prefs ->
            prefs[THEME_KEY]?.let {
                ThemeMode.valueOf(it)
            } ?: ThemeMode.AUTO
        }

    override suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { prefs ->
            prefs[THEME_KEY] = mode.name
        }
    }

    override fun languageFlow():  Flow<Language> =
        dataStore.data.map { prefs ->
            val stored = prefs[LANGUAGE_KEY]
            stored?.let { raw ->
                Language.entries.firstOrNull { it.tag == raw }
                    ?: runCatching { Language.valueOf(raw) }.getOrNull()
            } ?: Language.AUTO
        }

    override suspend fun setLanguage(language: Language) {
        dataStore.edit { prefs ->
            prefs[LANGUAGE_KEY] = language.tag
        }
    }
}
