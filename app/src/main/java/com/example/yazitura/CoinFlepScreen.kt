package com.example.yazitura
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun CoinFlipScreen() {

    var result by remember { mutableStateOf("") }
    var rotation by remember { mutableStateOf(0f) }

    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(1000),
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
                id = when (result) {
                    "YAZI" -> R.drawable.coin_yazi
                    "TURA" -> R.drawable.coin_tura
                    else -> R.drawable.coin_yazi
                }
            ),
            contentDescription = "Coin",
            modifier = Modifier
                .size(220.dp)
                .graphicsLayer {
                    rotationY = animatedRotation
                }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (result.isNotEmpty()) {
            Text(
                text = result,
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                scope.launch {
                    result = ""
                    rotation += 1800f
                    delay(1000)

                    result = if (Random.nextBoolean()) {
                        "YAZI"
                    } else {
                        "TURA"
                    }
                }
            }
        ) {
            Text("PARAYI AT")
        }
    }
}