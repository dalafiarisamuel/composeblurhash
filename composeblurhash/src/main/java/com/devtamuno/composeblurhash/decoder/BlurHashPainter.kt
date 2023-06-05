package com.devtamuno.composeblurhash.decoder

import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import com.devtamuno.composeblurhash.ExperimentalComposeBlurHash
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalComposeBlurHash
class BlurHashPainter(request: BlurHasDecoderRequest) : Painter(), RememberObserver {

    private var rememberScope: CoroutineScope? = null
    private val drawSize = MutableStateFlow(Size.Zero)
    private var painter: Painter? by mutableStateOf(null)
    internal var imageBitmap: ImageBitmap by mutableStateOf(ImageBitmap(1, 1))
    private var request: BlurHasDecoderRequest by mutableStateOf(request)
    internal var isPreview = false
    private var _painter: Painter? = null
        set(value) {
            field = value
            painter = value
        }

    override fun onRemembered() {

        if (rememberScope != null) return

        // Create a new scope to observe state and execute requests while we're remembered.
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        rememberScope = scope

        // Manually notify the child painter that we're remembered.
        (_painter as? RememberObserver)?.onRemembered()

        if (isPreview) return

        // Observe the current request and execute any emissions.
        scope.launch {
            snapshotFlow { request }
                .mapLatest { it.execute() }
                .catch { it.printStackTrace() }
                .collect(::updatePainter)
        }
    }

    private fun updatePainter(imageBitmap: ImageBitmap?) {
        if (imageBitmap == null) return

        this.imageBitmap = imageBitmap
        _painter = BitmapPainter(imageBitmap)

        if (rememberScope != null) {
            (_painter as? RememberObserver)?.onForgotten()
            (_painter as? RememberObserver)?.onRemembered()
        }
    }

    override fun onForgotten() {
        clear()
        (_painter as? RememberObserver)?.onForgotten()
    }

    override fun onAbandoned() {
        clear()
        (_painter as? RememberObserver)?.onAbandoned()
    }

    private fun clear() {
        rememberScope?.cancel()
        rememberScope = null
    }

    override val intrinsicSize: Size
        get() = painter?.intrinsicSize ?: Size.Unspecified


    override fun DrawScope.onDraw() {
        // Update the draw scope's current size.
        drawSize.value = size

        // Draw the current painter.
        painter?.apply { draw(size) }
    }
}