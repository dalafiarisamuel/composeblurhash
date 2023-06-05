package com.devtamuno.composeblurhash.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import com.devtamuno.composeblurhash.ExperimentalComposeBlurHash
import com.devtamuno.composeblurhash.decoder.BlurHasDecoderRequest
import com.devtamuno.composeblurhash.decoder.BlurHashPainter
import com.devtamuno.composeblurhash.decoder.ImageBitmapBrush

@Composable
@ExperimentalComposeBlurHash
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

@Suppress("unused")
@Composable
@ExperimentalComposeBlurHash
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


