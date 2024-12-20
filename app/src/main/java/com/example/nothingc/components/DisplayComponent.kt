package com.example.nothingc.components


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nothingc.CalculatorViewModel
import com.example.nothingc.scaledDp
import com.example.nothingc.scaledSp
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingSilver
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.Spacing.sm
import com.example.nothingc.ui.theme.appWideFont
import kotlinx.coroutines.delay

@Composable
internal fun DisplayComponent(modifier: Modifier = Modifier, state: CalculatorViewModel.ViewState) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(
                vertical = sm.scaledDp(),
                horizontal = sm.scaledDp()
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {

//            HistoryComponent(
//                modifier = Modifier
//                    .sizeIn(maxHeight = 130.scaledDp()),
//                    //.constrainAs(historyConst) {
//                    //    bottom.linkTo(displayConst.top, margin = 0.dp)
//                    //},
//                state = state
//            )

            Box(
                modifier = Modifier.align(Alignment.End),
            ){

                var input = state.num1
                if (state.operator != ""){
                    input += " " + state.operator + " " + state.num2
                }
                Text(
                    color = MaterialTheme.colors.onBackground,
                    text = input,
                    overflow = TextOverflow.Visible,
                    maxLines = 1,
                    fontFamily = appWideFont,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState(0), reverseScrolling = true)
                        .align(Alignment.CenterEnd),
                    fontSize = 60.scaledSp(),
                )

                if (state.num1 == "") BlinkingCursor(modifier = Modifier.align(Alignment.CenterEnd))
            }

            Text(
                color = MaterialTheme.colors.onBackground,
                text = state.result,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                fontFamily = appWideFont,
                fontSize = 34.scaledSp(),
                textAlign = TextAlign.End,
                modifier = Modifier
                    //.horizontalScroll(rememberScrollState(0), reverseScrolling = true)
                    .fillMaxWidth()
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
                        } else if (isSystemInDarkTheme()) NothingBlack else NothingSilver,
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
            text = "...",
            color = color,
            fontSize = 55.scaledSp(),
            fontFamily = appWideFont
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