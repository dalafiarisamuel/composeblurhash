# ComposeBlurHash [![ComposeBlurHash](https://jitpack.io/v/dalafiarisamuel/composeblurhash.svg)](https://jitpack.io/#dalafiarisamuel/composeblurhash)

The ComposeBlurHash is a component written in Jetpack Compose that comes with the required
implementation to exhibit an image with a blurred effect until the actual image gets downloaded from
the web.

# How to use:

- In build.gradle file, add this dependency

        implementation 'com.github.dalafiarisamuel:composeblurhash:latest_version'

# Code sample:

1. Using `rememberBlurHashPainter` component as a painter

```kotlin
@Composable
fun BlurHashPainterImage() {

    val blurHashPainter = rememberBlurHashPainter(
        blurString = "LvF7o6RiV@ofL4j?ozay4ptQkCfk",
        width = 4032,
        height = 3024,
    )

    Card(
        elevation = 24.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Image(
            painter = blurHashPainter,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .width(250.dp)
                .height(300.dp)
        )
    }
}

```

2. Using `rememberBlurHashPainter()` component as a `placeHolder`, `error` or `fallback` painter in [Coil](https://github.com/coil-kt/coil)

```kotlin
@Composable
fun BlurHashPainterCoilImage() {

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
            .wrapContentHeight(),
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
```

