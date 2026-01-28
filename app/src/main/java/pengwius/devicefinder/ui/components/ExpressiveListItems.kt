package pengwius.devicefinder.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import pengwius.devicefinder.ui.theme.ExpressiveListItemShapes

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
inline fun <T> ExpressiveListItems(
    items: List<T>,
    crossinline content: @Composable (item: T, shape: RoundedCornerShape) -> Unit
) {
    ExpressiveListItemsIndexed(items) { _, item, shape ->
        content(item, shape)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
inline fun <T> ExpressiveListItemsIndexed(
    items: List<T>,
    crossinline content: @Composable (index: Int, item: T, shape: RoundedCornerShape) -> Unit
) {
    items.forEachIndexed { index, item ->
        val shape = when {
            items.size == 1 -> ExpressiveListItemShapes.singleListItemShape
            index == 0 -> ExpressiveListItemShapes.topListItemShape
            index == items.lastIndex -> ExpressiveListItemShapes.bottomListItemShape
            else -> ExpressiveListItemShapes.middleListItemShape
        }
        content(index, item, shape)
    }
}
