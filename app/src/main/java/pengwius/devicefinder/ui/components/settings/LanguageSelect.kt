package pengwius.devicefinder.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pengwius.devicefinder.data.models.settings.Language
import org.koin.androidx.compose.getViewModel
import pengwius.devicefinder.ui.viewmodels.settings.SettingsViewModel
import pengwius.devicefinder.R

@Composable
fun LanguageSelect(
    navController: NavController
) {
    val viewModel: SettingsViewModel = getViewModel()
    val currentLanguage by viewModel.language.collectAsState(initial = Language.AUTO)
    val display = currentLanguage.displayName

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("language_list") }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = stringResource(R.string.language_label), style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(display),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(8.dp))
    }
}
