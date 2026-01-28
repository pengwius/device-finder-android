package pengwius.devicefinder.ui.theme

import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object ExpressiveListItemShapes {
    val topListItemShape =
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 4.dp, bottomEnd = 4.dp)
    val middleListItemShape = RoundedCornerShape(4.dp)
    val bottomListItemShape =
        RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
    val singleListItemShape =
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ButtonGroupDefaults.connectedSymmetricalLeadingButtonShape() = connectedLeadingButtonShapes()
    .copy(checkedShape = connectedLeadingButtonShape)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ButtonGroupDefaults.connectedSymmetricalMiddleButtonShape() = connectedMiddleButtonShapes()
    .copy(checkedShape = ShapeDefaults.Small)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ButtonGroupDefaults.connectedSymmetricalTrailingButtonShape() = connectedTrailingButtonShapes()
    .copy(checkedShape = ButtonGroupDefaults.connectedTrailingButtonShape)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ToggleButtonDefaults.symmetricalToggleButtonColors() = toggleButtonColors().copy(
    checkedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
    checkedContainerColor = MaterialTheme.colorScheme.secondaryContainer
)
