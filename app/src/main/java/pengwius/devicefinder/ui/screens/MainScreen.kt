package pengwius.devicefinder.ui.screens

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.core.view.WindowInsetsControllerCompat
import pengwius.devicefinder.ui.components.BottomNav
import pengwius.devicefinder.ui.components.TopBar
import pengwius.devicefinder.ui.screens.settings.SettingsScreen
import pengwius.devicefinder.ui.screens.settings.LanguageListScreen
import pengwius.devicefinder.ui.viewmodels.settings.SettingsViewModel
import pengwius.devicefinder.data.models.settings.ThemeMode
import org.koin.androidx.compose.getViewModel
import pengwius.devicefinder.navigation.NavItem
import pengwius.devicefinder.navigation.animatedComposable
import pengwius.devicefinder.ui.theme.LightColorScheme
import pengwius.devicefinder.ui.theme.DarkColorScheme

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: NavItem.Home.route

    val context = LocalContext.current
    val settingsViewModel: SettingsViewModel = getViewModel()
    val monetEnabled by settingsViewModel.monetEnabled.collectAsState()

    val mode by settingsViewModel.themeMode.collectAsState()
    val isDark = when (mode) {
        ThemeMode.AUTO -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val colorScheme = if (monetEnabled) {
        if (isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (isDark) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme
    ) {
        val view = LocalView.current

        val statusBarColor = MaterialTheme.colorScheme.background
        val lightStatusBarAppearance = !isDark

        if (!view.isInEditMode) {
            val window = (view.context as Activity).window
            SideEffect {
                window.statusBarColor = statusBarColor.toArgb()
                WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = lightStatusBarAppearance
            }
        }

        Scaffold(
            topBar = { TopBar(currentRoute = currentRoute, navController = navController) },
            bottomBar = {
                BottomNav(navController)
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavHost(navController = navController, startDestination = NavItem.Home.route) {
                    animatedComposable(NavItem.Home.route) { HomeScreen() }
                    animatedComposable(NavItem.Devices.route) { DevicesScreen() }
                    animatedComposable(NavItem.Settings.route) { SettingsScreen(navController) }
                    animatedComposable(NavItem.LanguageList.route) { LanguageListScreen(navController) }
                }
            }
        }
    }
}
