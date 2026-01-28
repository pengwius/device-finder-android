package pengwius.devicefinder.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import pengwius.devicefinder.R

sealed class NavItem(val route: String, val icon: ImageVector?, val label: Int) {
    object Home : NavItem("home", Icons.Default.Home, R.string.home)
    object Devices: NavItem("devices", Icons.Default.Devices, R.string.devices)
    object Settings : NavItem("settings", Icons.Default.Settings, R.string.settings)
    object LanguageList : NavItem("language_list", null, R.string.language_list_label)
}
