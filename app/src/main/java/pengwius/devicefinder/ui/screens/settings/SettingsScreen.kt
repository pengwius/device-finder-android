package pengwius.devicefinder.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pengwius.devicefinder.data.models.settings.Language
import pengwius.devicefinder.ui.components.settings.LanguageSelect
import pengwius.devicefinder.ui.components.settings.MonetToggle
import pengwius.devicefinder.ui.components.settings.ThemeToggle
import pengwius.devicefinder.ui.theme.ExpressiveListItemShapes
import org.koin.androidx.compose.getViewModel
import pengwius.devicefinder.ui.viewmodels.settings.SettingsViewModel
import pengwius.devicefinder.R

private sealed interface SettingItem {
    object Monet : SettingItem
    object Theme : SettingItem
    object Language : SettingItem
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val settingsViewModel: SettingsViewModel = getViewModel()

    val items: List<SettingItem> = listOf(
        SettingItem.Monet,
        SettingItem.Theme,
        SettingItem.Language
    )

    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    LaunchedEffect(savedStateHandle) {
        savedStateHandle?.getStateFlow<String?>("selected_language", null)
            ?.collect { result ->
                result?.let { raw ->
                    val lang = Language.entries.firstOrNull { it.tag == raw } ?: Language.AUTO
                    settingsViewModel.setLanguage(lang)
                    savedStateHandle.remove<String>("selected_language")
                }
            }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        items(items) { item ->
            Column(modifier = Modifier.fillMaxWidth()) {
                val title = when (item) {
                    SettingItem.Monet -> stringResource(R.string.monet_label)
                    SettingItem.Theme -> stringResource(R.string.theme_label)
                    SettingItem.Language -> stringResource(R.string.language_label)
                }

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = ExpressiveListItemShapes.singleListItemShape,
                    tonalElevation = 1.dp,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 0.dp, vertical = 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))

                        when (item) {
                            SettingItem.Monet -> MonetToggle()
                            SettingItem.Theme -> ThemeToggle()
                            SettingItem.Language -> LanguageSelect(navController)
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
