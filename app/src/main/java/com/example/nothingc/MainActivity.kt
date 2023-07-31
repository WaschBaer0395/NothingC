package com.example.nothingc

import android.content.Context
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
import com.example.nothingc.components.DisplayComponent
import com.example.nothingc.ui.theme.NothingBlack
import com.example.nothingc.ui.theme.NothingWhite
import com.example.nothingc.ui.theme.appWideFont
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.nothingc.components.CalcButtGridLayoutH
import com.example.nothingc.components.CalcButtGridLayoutV
import com.example.nothingc.components.MenuComponent
import com.example.nothingc.components.MenuPopup
import com.example.nothingc.ui.theme.NothingSilver
import java.util.Stack

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // adding a padding to respect gestures, status bar and navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemGestures())
            // Apply the insets as padding to the view. Here we're setting all of the
            // dimensions, but apply as appropriate to your layout. You could also
            // update the views margin if more appropriate.
            view.updatePadding(
                //insets.left, // gesture swipe
                //insets.top,  // status bar
                //insets.right, // gesture swipe
                //insets.bottom // android navbar/pill
            )
            // Return CONSUMED if we don't want the window insets to keep being passed
            // down to descendant views.
            WindowInsetsCompat.CONSUMED
        }

        window.decorView.setBackgroundColor(if(isSystemInDarkMode(this)) NothingBlack.hashCode() else NothingSilver.hashCode())

        setContent {
            DetectOrientation()
        }
    }
}
fun isSystemInDarkMode(context: Context): Boolean {
    val configuration = context.resources.configuration
    return configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

val stack = Stack<String>()

@Composable
private fun DetectOrientation() {

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            CalculatorTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state =  viewModel.viewState.collectAsState(
                        initial = CalculatorViewModel.ViewState(
                            stack,
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
                            stack,
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
    val topMargin = 10.scaledDp()
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.scaledDp(), vertical = 24.scaledDp())
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ){
            val (navConstr, displayConst, calcButtGridConst) = createRefs()

            MenuComponent(
                modifier = Modifier
                .fillMaxWidth()
                .constrainAs(navConstr) {
                    top.linkTo(parent.top, margin = topMargin)
                }
            )

            DisplayComponent(
                modifier = Modifier
                    .constrainAs(displayConst){
                        bottom.linkTo(calcButtGridConst.top, margin = 0.dp)
                        top.linkTo(navConstr.top, margin = 0.dp)
                    },
                state = state
            )
            CalcButtGridLayoutV(
                modifier = Modifier
                    .constrainAs(calcButtGridConst){
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    },
                dispatcher = dispatcher
            )
        }
    }
}

@Composable
private fun CalcScreenHorizontal(state: CalculatorViewModel.ViewState, dispatcher: (ActionType) -> Unit) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.scaledDp(), vertical = 16.scaledDp())
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

            DisplayComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(displayConst) {
                        bottom.linkTo(calcButtGridConst.top, margin = 0.dp)
                    },
                state = state
            )

            CalcButtGridLayoutH(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(calcButtGridConst) {
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    },
                dispatcher = dispatcher
            )
        }
    }
}

@Preview(showBackground = true, widthDp = (1080*0.4).toInt(), heightDp = (2400*0.4).toInt())
@Composable
private fun CalcScreenPreviewVertical() {
    CalculatorTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) NothingBlack else NothingSilver)
        ) {
            CalcScreenVertical(CalculatorViewModel.ViewState(stack, "+", "0", "0")) {}
        }
    }
}


@Preview(showBackground = true, widthDp = (2400*0.4).toInt(), heightDp = (1080*0.4).toInt())
@Composable
private fun CalcScreenPreviewHorizontal() {
    CalculatorTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) NothingBlack else NothingSilver)
        ) {
            CalcScreenHorizontal(CalculatorViewModel.ViewState(stack, "+", "0", "0")) {}
        }
    }
}