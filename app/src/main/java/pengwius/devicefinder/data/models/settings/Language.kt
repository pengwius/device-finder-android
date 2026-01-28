package pengwius.devicefinder.data.models.settings

import pengwius.devicefinder.R

enum class Language(val tag: String, val displayName: Int) {
    AUTO("auto", R.string.language_auto),
    ENGLISH("en", R.string.language_en),
    POLISH("pl", R.string.language_pl);
}
