package com.example.nothingc


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.ndot55
import com.example.nothingc.ui.theme.spacing
import kotlinx.coroutines.delay

@Composable
internal fun DisplayComponent(modifier: Modifier = Modifier, state: CalculatorViewModel.ViewState) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(
                vertical = 16.scaledDp(),
                horizontal = 16.scaledDp()
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Box(
                modifier = Modifier.align(Alignment.End),
            ){
                Text(
                    color = MaterialTheme.colors.onBackground,
                    text = state.num1 + " " + state.operator + " " + state.num2,
                    overflow = TextOverflow.Visible,
                    maxLines = 1,
                    fontFamily = ndot55,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .sizeIn(maxHeight = 80.scaledDp())
                        .align(Alignment.CenterEnd),
                    fontSize = 64.scaledSp(),
                )

                if (state.num1 == "") BlinkingCursor(modifier = Modifier.align(Alignment.CenterEnd))
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            Text(
                color = MaterialTheme.colors.onBackground,
                text = state.result,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                fontFamily = ndot55,
                fontSize = 34.scaledSp(),
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = 50.scaledDp())
            )
        }
    }
}

@Composable
fun BlinkingCursor(modifier: Modifier = Modifier) {
    var isBlinking by remember { mutableStateOf(true) }

    LaunchedEffect(isBlinking) {
        while (isBlinking) {
            delay(400) // Delay between blink states
            isBlinking = !isBlinking
        }
    }

    val color by animateColorAsState(
        targetValue = if (isBlinking){
                            (if (isSystemInDarkTheme()) NothingWhite else NothingBlack)
                        } else if (isSystemInDarkTheme()) NothingBlack else NothingWhite,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400), // Duration of each blink state
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .width(45.scaledDp()),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = modifier.rotate(-90F),
            text = "....",
            color = color,
            fontSize = 56.scaledSp(),
            fontFamily = ndot55
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayComponentPreview() {
    Box(modifier = Modifier.padding(10.scaledDp())) {
        DisplayComponent(state = CalculatorViewModel.ViewState("0","+","0","0"))
    }
}