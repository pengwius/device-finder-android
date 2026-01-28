package pengwius.devicefinder.data.settings

import kotlinx.coroutines.flow.Flow
import pengwius.devicefinder.data.models.settings.ThemeMode
import pengwius.devicefinder.data.models.settings.Language

interface SettingsDataStore {
    fun monetEnabledFlow(): Flow<Boolean>
    suspend fun setMonetEnabled(enabled: Boolean)

    fun themeModeFlow(): Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)

    fun languageFlow(): Flow<Language>
    suspend fun setLanguage(language: Language)
}
