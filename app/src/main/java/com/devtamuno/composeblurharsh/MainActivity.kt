package com.devtamuno.composeblurharsh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.devtamuno.composeblurharsh.ui.theme.ComposeBlurHarshTheme
import com.devtamuno.composeblurhash.rememberBlurHashBrush
import com.devtamuno.composeblurhash.rememberBlurHashPainter

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
                        Spacer(modifier = Modifier.height(10.dp))
                        BlurHashBrush()

                    }
                }
            }
        }
    }

    @Composable
    fun CoilImage(modifier: Modifier = Modifier) {

        val painter: AsyncImagePainter =
            rememberAsyncImagePainter("https://images.unsplash.com/photo-1513002749550-c59d786b8e6c?ixlib=rb-4.0.3&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max")

        Card(
            elevation = 24.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .then(modifier),
        ) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = null,
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
                    blurString = "LRG,SAxY%MRj0L%Lt6xt8^ofs:jY",
                    width = 2848,
                    height = 4272

                ),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                modifier = Modifier
                    .width(250.dp)
                    .height(300.dp)
            )
        }
    }

    @Composable
    fun BlurHashBrush(modifier: Modifier = Modifier) {

        val rememberBlurHashBrush = rememberBlurHashBrush(
            blurString = "LRG,SAxY%MRj0L%Lt6xt8^ofs:jY",
            width = 2848,
            height = 4272,
            showBlurBackgroundImage = true
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    rememberBlurHashBrush,
                    shape = RoundedCornerShape(10.dp)
                )
                .then(modifier)
        )
    }
}
