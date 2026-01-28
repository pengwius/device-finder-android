package pengwius.devicefinder.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.MaterialTheme
import pengwius.devicefinder.R
import pengwius.devicefinder.navigation.NavItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentRoute: String?,
    navController: NavHostController? = null
) {
    val titleRes = when (currentRoute) {
        NavItem.Home.route -> R.string.home
        NavItem.Devices.route -> R.string.devices
        NavItem.Settings.route -> R.string.settings
        NavItem.LanguageList.route -> R.string.language_list_label
        else -> 0
    }

    TopAppBar(
        title = { Text(if (titleRes != 0) stringResource(titleRes) else "") },
        navigationIcon = {
            if (currentRoute == NavItem.LanguageList.route) {
                IconButton(onClick = { navController?.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
