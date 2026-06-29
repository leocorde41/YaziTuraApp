package com.example.yazitura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CoinFlipScreen()
                }
            }
        }
    }
}

@Composable
fun CoinFlipScreen() {

    var result by remember {
        mutableStateOf("Parayı At")
    }

    var rotation by remember {
        mutableStateOf(0f)
    }

    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 1000),
        label = ""
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
                Image(
                painter = painterResource(
                    id = if (result == "YAZI")
                        R.drawable.coin_yazi
                    else
                        R.drawable.coin_tura
                ),
        contentDescription = "Coin",
        modifier = Modifier.graphicsLayer {
            rotationY = animatedRotation
        }
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = result,
        fontSize = 30.sp
    )

        Button(
            onClick = {

                scope.launch {

                    rotation += 1800f

                    delay(1000)

                    result =
                        if (Random.nextBoolean())
                            "YAZI"
                        else
                            "TURA"
                }

            }
        ) {
            Text("PARAYI AT")
        }
    }
}