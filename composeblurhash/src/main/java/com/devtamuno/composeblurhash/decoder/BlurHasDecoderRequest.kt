package com.devtamuno.composeblurhash.decoder

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

class BlurHasDecoderRequest(
    private val blurString: String,
    private var width: Int,
    private var height: Int,
    private val scale: Float = 0.1f,
) {

    suspend fun execute(): ImageBitmap? {
        val size = 100 * scale

        if (width > height) {
            height = (size * height / width).toInt()
            width = size.toInt()
        } else {
            width = (size * width / height).toInt()
            height = size.toInt()
        }

        val bitmap = BlurHashDecoder.decode(
            blurHash = blurString, width = width, height = height, useCache = false
        )
        return bitmap?.asImageBitmap()
    }
}