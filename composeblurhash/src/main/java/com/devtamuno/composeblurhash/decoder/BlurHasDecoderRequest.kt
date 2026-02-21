package com.devtamuno.composeblurhash.decoder

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

/**
 * A request to decode a BlurHash string into an [ImageBitmap].
 *
 * @param blurString The BlurHash string to decode.
 * @param width The target width of the decoded bitmap.
 * @param height The target height of the decoded bitmap.
 * @param scale The scaling factor to apply to the internal decoding size (default is 0.1f).
 */
class BlurHasDecoderRequest(
    private val blurString: String,
    private var width: Int,
    private var height: Int,
    private val scale: Float = 0.1f,
) {

    /**
     * Executes the decoding request and returns the resulting [ImageBitmap].
     *
     * This method scales the dimensions based on the [scale] factor and uses [BlurHashDecoder]
     * to generate the blurred placeholder.
     *
     * @return An [ImageBitmap] representing the blurred placeholder, or null if decoding fails.
     */
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
