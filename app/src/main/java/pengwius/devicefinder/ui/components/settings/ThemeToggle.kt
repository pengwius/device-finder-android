package pengwius.devicefinder.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import pengwius.devicefinder.ui.viewmodels.settings.SettingsViewModel
import pengwius.devicefinder.data.models.settings.ThemeMode
import pengwius.devicefinder.R

@Composable
fun ThemeToggle() {
    val viewModel: SettingsViewModel = getViewModel()
    val selectedTheme by viewModel.themeMode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ThemeMode.entries.forEach { mode ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.setThemeMode(mode) }
                    .padding(vertical = 4.dp, horizontal = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedTheme == mode,
                    onClick = { viewModel.setThemeMode(mode) },
                    modifier = Modifier
                        .padding(start = 0.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = when (mode) {
                    ThemeMode.AUTO -> stringResource(R.string.theme_auto)
                    ThemeMode.LIGHT -> stringResource(R.string.theme_light)
                    ThemeMode.DARK -> stringResource(R.string.theme_dark)
                })
            }
        }
    }
}
