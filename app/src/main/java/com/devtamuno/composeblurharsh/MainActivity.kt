

package com.devtamuno.composeblurharsh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.devtamuno.composeblurharsh.ui.theme.ComposeBlurHarshTheme
import com.devtamuno.composeblurhash.ExperimentalComposeBlurHash
import com.devtamuno.composeblurhash.ext.rememberBlurHashPainter
@ExperimentalComposeBlurHash
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBlurHarshTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()

                    ) {

                        CoilImage()
                        Spacer(modifier = Modifier.height(10.dp))
                        BlurHashPainterImage()

                    }
                }
            }
        }
    }

    @Composable
    fun CoilImage(modifier: Modifier = Modifier) {

        val placeHolder = rememberBlurHashPainter(
            blurString = "LvF7o6RiV@ofL4j?ozay4ptQkCfk",
            width = 4032,
            height = 3024,
        )

        Card(
            elevation = 24.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
        ) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1587590010936-300da0d70b9e",
                contentDescription = null,
                placeholder = placeHolder,
                contentScale = ContentScale.FillBounds,
                error = placeHolder,
                modifier = Modifier
                    .width(250.dp)
                    .height(300.dp)
            )
        }
    }

    @Composable
    fun BlurHashPainterImage(modifier: Modifier = Modifier) {

        Card(
            elevation = 24.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
        ) {
            Image(
                painter = rememberBlurHashPainter(
                    blurString = "LvF7o6RiV@ofL4j?ozay4ptQkCfk",
                    width = 4032,
                    height = 3024,
                ),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .height(300.dp)
            )
        }
    }
}
