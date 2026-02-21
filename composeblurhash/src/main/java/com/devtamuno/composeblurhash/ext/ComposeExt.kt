package com.devtamuno.composeblurhash.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import com.devtamuno.composeblurhash.decoder.BlurHasDecoderRequest
import com.devtamuno.composeblurhash.decoder.BlurHashPainter
import com.devtamuno.composeblurhash.decoder.ImageBitmapBrush

/**
 * Creates and remembers a [BlurHashPainter] that decodes the given [blurString].
 *
 * @param blurString The BlurHash string to decode.
 * @param width The target width of the decoded bitmap.
 * @param height The target height of the decoded bitmap.
 * @param scale The scaling factor to apply to the internal decoding size (default is 0.1f).
 * @return A [BlurHashPainter] that will draw the blurred placeholder once decoded.
 */
@Composable
fun rememberBlurHashPainter(
    blurString: String,
    width: Int,
    height: Int,
    scale: Float = 0.1f,
): BlurHashPainter {

    val request = BlurHasDecoderRequest(
        blurString = blurString, width = width, height = height, scale = scale
    )
    val painter = remember { BlurHashPainter(request) }
    painter.isPreview = LocalInspectionMode.current

    return painter

}

/**
 * Creates and remembers a [Brush] that can be used for blurred background effects.
 *
 * @param blurString The BlurHash string to decode.
 * @param width The target width of the decoded bitmap.
 * @param height The target height of the decoded bitmap.
 * @param showBlurBackgroundImage Whether to show the blurred image or a transparent brush.
 * @return A [Brush] that either tiles the blurred image or is transparent.
 */
@Suppress("unused")
@Composable
private fun rememberBlurHashBrush(
    blurString: String,
    width: Int,
    height: Int,
    showBlurBackgroundImage: Boolean,
): Brush {

    return if (showBlurBackgroundImage) {

        val request = BlurHasDecoderRequest(
            blurString = blurString, width = width, height = height
        )
        val painter = remember { BlurHashPainter(request) }
        painter.isPreview = LocalInspectionMode.current
        ImageBitmapBrush(painter.imageBitmap)

    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero,
        )
    }
}
