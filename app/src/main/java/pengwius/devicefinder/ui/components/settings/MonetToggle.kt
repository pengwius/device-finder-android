package pengwius.devicefinder.ui.components.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
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
import pengwius.devicefinder.R

@Composable
fun MonetToggle() {
    val viewModel: SettingsViewModel = getViewModel()
    val monetEnabled by viewModel.monetEnabled.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.use_monet_label), modifier = Modifier.weight(1f))
        Switch(
            checked = monetEnabled,
            onCheckedChange = { viewModel.setMonetEnabled(it) }
        )
    }
}
