package com.example.nothingc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nothingc.ui.theme.CalculatorTheme
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.nothingc.components.CalculatorButtonsGridLayout
import com.example.nothingc.components.DisplayComponent
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.appWideFont
import android.content.res.Configuration
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state =
                    viewModel.viewState.collectAsState(
                        initial = CalculatorViewModel.ViewState(
                            "0",
                            "+",
                            "0",
                            "0"
                        )
                    ).value
                DetectOrientation(state)
            }
        }
    }
}
@Composable
private fun DetectOrientation(state: CalculatorViewModel.ViewState) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state =
                    viewModel.viewState.collectAsState(
                        initial = CalculatorViewModel.ViewState(
                            "0",
                            "+",
                            "0",
                            "0"
                        )
                    ).value
                CalcScreenHorizontal(state) {
                    viewModel.dispatch(it)
                }
            }
        }
        else -> {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state =
                    viewModel.viewState.collectAsState(
                        initial = CalculatorViewModel.ViewState(
                            "0",
                            "+",
                            "0",
                            "0"
                        )
                    ).value
                CalcScreenVertical(state) {
                    viewModel.dispatch(it)
                }
            }
        }
    }
}


@Composable
private fun CalcScreenVertical(state: CalculatorViewModel.ViewState, dispatcher: (ActionType) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.scaledDp(), vertical = 16.scaledDp())
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp)
            ){
                val (navConstr, historyConst, displayConst, calcButtGridConst) = createRefs()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(navConstr) {
                            bottom.linkTo(historyConst.top, margin = 0.dp)
                        }
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Vertical",
                        fontFamily = appWideFont,
                        fontSize = 15.scaledSp(),
                        color = if (isSystemInDarkTheme()) NothingWhite else NothingBlack,
                        textAlign = TextAlign.Center
                    )
                }

                DisplayComponent(
                    modifier = Modifier
                        .constrainAs(displayConst){
                            bottom.linkTo(calcButtGridConst.top, margin = 0.dp)
                        },
                    state = state
                )
                CalculatorButtonsGridLayout(
                    modifier = Modifier
                        .constrainAs(calcButtGridConst){
                            bottom.linkTo(parent.bottom, margin = 0.dp)
                        },
                    dispatcher = dispatcher)
            }
        }
    }
}

@Composable
private fun CalcScreenHorizontal(state: CalculatorViewModel.ViewState, dispatcher: (ActionType) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.scaledDp(), vertical = 16.scaledDp())
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp)
            ){
                val (navConstr, historyConst, displayConst, calcButtGridConst) = createRefs()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(navConstr) {
                            bottom.linkTo(historyConst.top, margin = 0.dp)
                        }
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Horizontal",
                        fontFamily = appWideFont,
                        fontSize = 15.scaledSp(),
                        color = if (isSystemInDarkTheme()) NothingWhite else NothingBlack,
                        textAlign = TextAlign.Center
                    )
                }

//                DisplayComponent(
//                    modifier = Modifier
//                        .constrainAs(displayConst){
//                            bottom.linkTo(calcButtGridConst.top, margin = 0.dp)
//                        },
//                    state = state
//                )
//                CalculatorButtonsGridLayout(
//                    modifier = Modifier
//                        .constrainAs(calcButtGridConst){
//                            bottom.linkTo(parent.bottom, margin = 0.dp)
//                        },
//                    dispatcher = dispatcher
//                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalcScreenPreviewVertical() {
    CalculatorTheme {
        CalcScreenVertical(CalculatorViewModel.ViewState("0","+","0","0")){}
    }
}

@Preview(showBackground = true)
@Composable
private fun CalcScreenPreviewHorizontal() {
    CalculatorTheme {
        CalcScreenHorizontal(CalculatorViewModel.ViewState("0","+","0","0")){}
    }
}