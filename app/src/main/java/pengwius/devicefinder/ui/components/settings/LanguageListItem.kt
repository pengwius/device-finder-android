package pengwius.devicefinder.ui.components.settings

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import pengwius.devicefinder.data.models.settings.Language
import pengwius.devicefinder.ui.viewmodels.settings.SettingsViewModel
import pengwius.devicefinder.R

@Composable
fun LanguageListItem(
    language: Language,
    shape: RoundedCornerShape,
    navController: NavController
) {
    val viewModel: SettingsViewModel = getViewModel()
    val currentLang by viewModel.language.collectAsState(initial = Language.AUTO)
    val effectiveSelected = currentLang
    val coroutineScope = rememberCoroutineScope()

    val label = language.displayName
    val code = language.tag
    val isSelected = language == effectiveSelected

    val background = MaterialTheme.colorScheme.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                coroutineScope.launch {
                    try {
                        viewModel.setLanguage(language)
                    } catch (_: Throwable) { /* ignore */ }
                }
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("selected_language", language.tag)
                navController.popBackStack()
            }
            .animateContentSize()
            .padding(vertical = 2.dp),
        color = background,
        shape = shape,
        tonalElevation = 1.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Spacer(modifier = Modifier.size(8.dp))

            Column {
                Text(
                    text = stringResource(label),
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (isSelected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = code,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (isSelected) {
                Text(
                    text = stringResource(R.string.selected_language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
