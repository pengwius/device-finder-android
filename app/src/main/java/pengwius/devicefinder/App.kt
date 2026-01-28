package pengwius.devicefinder

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import androidx.datastore.preferences.preferencesDataStore
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import pengwius.devicefinder.di.appModule

val Context.dataStore by preferencesDataStore(name = "settings")

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
