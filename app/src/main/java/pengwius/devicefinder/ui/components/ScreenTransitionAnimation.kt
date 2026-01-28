package pengwius.devicefinder.ui.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.navigation.NavBackStackEntry

object ScreenTransitionAnimation {
    fun scaleIntoContainer(): EnterTransition {
        return scaleIn(
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            initialScale = 0.85f
        ) + fadeIn(animationSpec = tween(durationMillis = 300))
    }

    fun scaleOutOfContainer(): ExitTransition {
        return scaleOut(
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            targetScale = 1.1f
        ) + fadeOut(animationSpec = tween(durationMillis = 300))
    }

    fun popScaleIntoContainer(): EnterTransition {
        return scaleIn(
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            initialScale = 1.1f
        ) + fadeIn(animationSpec = tween(durationMillis = 300))
    }

    fun popScaleOutOfContainer(): ExitTransition {
        return scaleOut(
            animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
            targetScale = 0.85f
        ) + fadeOut(animationSpec = tween(durationMillis = 300))
    }

    val noClipSizeTransform: AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform = {
        SizeTransform(clip = false)
    }
}
