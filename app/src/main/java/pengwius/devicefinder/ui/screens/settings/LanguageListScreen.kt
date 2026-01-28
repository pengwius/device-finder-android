package pengwius.devicefinder.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
import pengwius.devicefinder.ui.components.ExpressiveListItems
import pengwius.devicefinder.data.models.settings.Language
import pengwius.devicefinder.ui.components.settings.LanguageListItem
import pengwius.devicefinder.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageListScreen(
    navController: NavController
) {
    val languages = Language.entries

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Text(
                text = stringResource(R.string.select_language),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            ExpressiveListItems(languages) { language, shape ->
                LanguageListItem(
                    language,
                    shape,
                    navController
                )
            }
        }

        item {
            Box(modifier = Modifier.height(12.dp))
        }
    }
}
