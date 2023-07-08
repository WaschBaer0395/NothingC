package com.example.nothingc


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nothingc.ui.theme.ndot55
import com.example.nothingc.ui.theme.spacing

@Composable
internal fun DisplayComponent(modifier: Modifier = Modifier, state: CalculatorViewModel.ViewState) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(
                vertical = MaterialTheme.spacing.md,
                horizontal = MaterialTheme.spacing.sm
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Text(
                color = MaterialTheme.colors.onBackground,
                text = state.num1 + " " + state.operator + " " + state.num2,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                fontFamily = ndot55,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().sizeIn(maxHeight = 80.dp),
                fontSize = 64.nonScaledSp,
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

            Text(
                color = MaterialTheme.colors.onBackground,
                text = state.result,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                fontFamily = ndot55,
                fontSize = 34.nonScaledSp,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().sizeIn(maxHeight = 50.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayComponentPreview() {
    Box(modifier = Modifier.padding(10.dp)) {
        DisplayComponent(state = CalculatorViewModel.ViewState("0","+","0","0"))
    }
}