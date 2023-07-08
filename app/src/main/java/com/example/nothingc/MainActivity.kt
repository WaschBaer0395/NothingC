package com.example.nothingc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nothingc.ui.theme.CalculatorTheme
import com.example.nothingc.ui.theme.spacing
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.ndot55

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        // forcing the app to fullscreen (behind transparent status bar)
//        WindowCompat.setDecorFitsSystemWindows(window, true)
//
//        // adding a padding to respect gestures, statusbar and navigatiom bar
//        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
//            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
//            // Apply the insets as padding to the view. Here we're setting all of the
//            // dimensions, but apply as appropriate to your layout. You could also
//            // update the views margin if more appropriate.
//            view.updatePadding(
//                insets.left, // gesture swipe
//                insets.top,  // status bar
//                insets.right, // gesture swipe
//                insets.bottom // android navbar/pill
//            )
//            // Return CONSUMED if we don't want the window insets to keep being passed
//            // down to descendant views.
//            WindowInsetsCompat.CONSUMED
//        }
//
        // coloring the background
//        window.decorView.setBackgroundColor(0xFFD7D8D8.toInt())

            setContent {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state =
                    viewModel.viewState.collectAsState(initial = CalculatorViewModel.ViewState("0","+","0","0")).value
                CalcScreen(state) {
                    viewModel.dispatch(it)
                }
            }
        }
    }
}

@Composable
private fun CalcScreen(state: CalculatorViewModel.ViewState, dispatcher: (ActionType) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.sm, vertical = MaterialTheme.spacing.sm)
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
                        text = "Hier kommen Nav/Settings buttons hin",
                        fontFamily = ndot55,
                        fontSize = 15.nonScaledSp,
                        color = if (isSystemInDarkTheme()) NothingWhite else NothingBlack,
                        textAlign = TextAlign.Center
                    )
                }

                HistoryComponent(
                    modifier = Modifier
                        .sizeIn(maxHeight = 130.dp)
                        .constrainAs(historyConst) {
                            bottom.linkTo(displayConst.top, margin = 0.dp)
                        },
                    state = state
                )
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
private fun CalculatorButtonsGridLayout(modifier: Modifier = Modifier, dispatcher: (ActionType) -> Unit) {
    val buttons = listOf(
        ActionType.Clear,
        ActionType.Operator(Operators.Power),
        ActionType.Percentage,
        ActionType.Operator(Operators.Divide),
        ActionType.Number(7),
        ActionType.Number(8),
        ActionType.Number(9),
        ActionType.Operator(Operators.Multiply),
        ActionType.Number(4),
        ActionType.Number(5),
        ActionType.Number(6),
        ActionType.Operator(Operators.Subtract),
        ActionType.Number(3),
        ActionType.Number(2),
        ActionType.Number(1),
        ActionType.Operator(Operators.Add),
        ActionType.Number(0),
        ActionType.Decimal,
        ActionType.Delete,
        ActionType.Calculate,
    )

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        content = {
            items(buttons) {
                CalculatorButtonComponent(
                    modifier = Modifier.aspectRatio(1f),
                    calculatorButtonComponentDesign = it.calculatorButtonComponentDesign,
                    symbol = it.symbol
                ) {
                    dispatcher(it)
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
private fun CalcScreenPreview() {
    CalculatorTheme {
        CalcScreen(CalculatorViewModel.ViewState("0","+","0","0")){}
    }
}