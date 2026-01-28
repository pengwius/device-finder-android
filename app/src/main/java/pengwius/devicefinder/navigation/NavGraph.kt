package pengwius.devicefinder.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import pengwius.devicefinder.ui.components.ScreenTransitionAnimation.noClipSizeTransform
import pengwius.devicefinder.ui.components.ScreenTransitionAnimation.popScaleIntoContainer
import pengwius.devicefinder.ui.components.ScreenTransitionAnimation.popScaleOutOfContainer
import pengwius.devicefinder.ui.components.ScreenTransitionAnimation.scaleIntoContainer
import pengwius.devicefinder.ui.components.ScreenTransitionAnimation.scaleOutOfContainer

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.animatedComposable(
    route: String,
    content: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { scaleIntoContainer() },
        exitTransition = { scaleOutOfContainer() },
        popEnterTransition = { popScaleIntoContainer() },
        popExitTransition = { popScaleOutOfContainer() },
        sizeTransform = noClipSizeTransform
    ) {
        content()
    }
}
