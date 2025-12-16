package com.example.hpoke.core.ui

import android.graphics.Bitmap
import android.util.LruCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PokemonPaletteCache {
    private val lock = Any()
    private val cache = LruCache<String, Int>(250)

    fun get(key: String?): Color? = key?.let {
        synchronized(lock) { cache.get(it) }?.let(::Color)
    }

    fun put(key: String, color: Color) {
        synchronized(lock) { cache.put(key, color.toArgb()) }
    }
}

suspend fun extractPaletteColorFast(
    bitmap: Bitmap,
    fallback: Color,
    targetSizePx: Int = 64,
): Color = withContext(Dispatchers.Default) {

    // 1️⃣ Downscale
    val scaled = Bitmap.createScaledBitmap(
        bitmap,
        targetSizePx,
        (bitmap.height * targetSizePx / bitmap.width).coerceAtLeast(1),
        true
    )

    // 2️⃣ Remove white / transparent background
    val cleaned = removeNearWhitePixels(scaled)

    // 3️⃣ Palette
    val palette = Palette.from(cleaned)
        .clearFilters()
        .maximumColorCount(8)
        .generate()

    val rgb = palette.vibrantSwatch?.rgb
        ?: palette.mutedSwatch?.rgb
        ?: palette.darkVibrantSwatch?.rgb
        ?: palette.dominantSwatch?.rgb

    rgb?.let { Color(it) } ?: fallback
}

fun normalizePokemonBackgroundSafe(
    extracted: Color,
    fallback: Color,
    targetSaturation: Float = 0.28f,
    targetLightness: Float = 0.65f,
): Color {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(extracted.toArgb(), hsl)

    // If extracted is too dark (common with shadows/transparent sprites), fallback
    if (hsl[2] < 0.18f) {
        ColorUtils.colorToHSL(fallback.toArgb(), hsl)
    }

    // Normalize
    hsl[1] = targetSaturation
    hsl[2] = targetLightness

    return Color(ColorUtils.HSLToColor(hsl))
}

private fun removeNearWhitePixels(
    bitmap: Bitmap,
    whiteThreshold: Int = 245
): Bitmap {
    val w = bitmap.width
    val h = bitmap.height
    val out = bitmap.copy(Bitmap.Config.ARGB_8888, true)

    val pixels = IntArray(w * h)
    out.getPixels(pixels, 0, w, 0, 0, w, h)

    for (i in pixels.indices) {
        val c = pixels[i]
        val a = android.graphics.Color.alpha(c)
        val r = android.graphics.Color.red(c)
        val g = android.graphics.Color.green(c)
        val b = android.graphics.Color.blue(c)

        // remove transparent or near-white pixels
        if (a < 40 || (r > whiteThreshold && g > whiteThreshold && b > whiteThreshold)) {
            pixels[i] = android.graphics.Color.TRANSPARENT
        }
    }

    out.setPixels(pixels, 0, w, 0, 0, w, h)
    return out
}
