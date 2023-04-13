package com.devtamuno.composeblurhash.decoder

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode

internal class ImageBitmapBrush(private val imageBitmap: ImageBitmap) : ShaderBrush() {
    override val intrinsicSize: Size
        get() = Size(imageBitmap.width.toFloat(), imageBitmap.height.toFloat())

    override fun createShader(size: Size): Shader {

        return ImageShader(
            image = imageBitmap,
            tileModeX = TileMode.Mirror,
            tileModeY = TileMode.Mirror
        )
    }
}