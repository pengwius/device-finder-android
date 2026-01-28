package pengwius.devicefinder.di

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import pengwius.devicefinder.data.settings.SettingsDataStoreImpl
import pengwius.devicefinder.ui.viewmodels.settings.SettingsViewModel
import pengwius.devicefinder.dataStore
import pengwius.devicefinder.data.settings.SettingsDataStore

val appModule = module {
    single<SettingsDataStore> { SettingsDataStoreImpl(androidContext().dataStore) }

    viewModel { SettingsViewModel(get()) }
}
