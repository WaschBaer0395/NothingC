package com.example.nothingc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.nothingc.ui.theme.ndot55

private val historyList =
    mutableListOf("1 + 2 = 3", "6 / 3 = 2", "5 x 2 = 10", "6 - 2 = 4", "7 - 2 = 5", "2 ^ 2 = 4", "4 ^ 2 = 16")

@Composable
internal fun HistoryComponent(modifier: Modifier = Modifier, state: CalculatorViewModel.ViewState) {
    LazyColumn(
        reverseLayout = true,
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(
            items = historyList,
            itemContent = {country ->
                Text(
                    color = MaterialTheme.colors.onBackground,
                    text = country,
                    overflow = TextOverflow.Visible,
                    maxLines = 1,
                    fontFamily = ndot55,
                    fontSize = 24.scaledSp(),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryComponentPreview() {
    Box(modifier = Modifier.padding(10.scaledDp())) {
        HistoryComponent(state = CalculatorViewModel.ViewState("0","+","0","0"))
    }
}