package pengwius.devicefinder.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import pengwius.devicefinder.ui.screens.MainScreen
import androidx.lifecycle.lifecycleScope
import androidx.core.os.LocaleListCompat
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import java.util.Locale
import pengwius.devicefinder.data.settings.SettingsDataStoreImpl
import pengwius.devicefinder.data.models.settings.Language
import pengwius.devicefinder.dataStore
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var lastAppliedTag: String? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lastAppliedTag = savedInstanceState?.getString("last_applied_tag")

        val settingsDataStore = SettingsDataStoreImpl(this.dataStore)

        val initialLang = runBlocking { settingsDataStore.languageFlow().first() }
        val initialTag = if (initialLang == Language.AUTO) Locale.getDefault().toLanguageTag() else initialLang.tag

        applyAppLocale(initialTag)
        lastAppliedTag = initialTag

        lifecycleScope.launch {
            settingsDataStore.languageFlow().collectLatest { lang ->
                val tag = if (lang == Language.AUTO) {
                    Locale.getDefault().toLanguageTag()
                } else {
                    lang.tag
                }
                if (lastAppliedTag != tag) {
                    applyAppLocale(tag)
                    lastAppliedTag = tag
                    this@MainActivity.recreate()
                }
            }
        }

        setContent {
            MainScreen()
        }
    }

    private fun applyAppLocale(tag: String) {
        try {
            val localesCompat = LocaleListCompat.forLanguageTags(tag)
            AppCompatDelegate.setApplicationLocales(localesCompat)

            applyLocaleToResources(tag)
        } catch (e: Exception) {
            Log.e("MainActivity", "Failed to apply locale $tag: ${e.message}")
        }
    }

    private fun applyLocaleToResources(tag: String) {
        val locale = try {
            if (tag.isBlank()) Locale.getDefault() else Locale.forLanguageTag(tag)
        } catch (_: Exception) {
            Locale.getDefault()
        }

        try {
            val res = resources
            val config: Configuration = Configuration(res.configuration)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = android.os.LocaleList(locale)
                config.setLocales(localeList)
            } else {
                @Suppress("DEPRECATION")
                config.locale = locale
            }
            res.updateConfiguration(config, res.displayMetrics)

            val appRes = applicationContext.resources
            val appConfig = Configuration(appRes.configuration)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = android.os.LocaleList(locale)
                appConfig.setLocales(localeList)
            } else {
                @Suppress("DEPRECATION")
                appConfig.locale = locale
            }
            appRes.updateConfiguration(appConfig, appRes.displayMetrics)
        } catch (e: Exception) {
            Log.e("MainActivity", "applyLocaleToResources failed: ${e.message}")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("last_applied_tag", lastAppliedTag)
    }
}
