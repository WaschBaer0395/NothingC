package com.example.nothingc.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.nothingc.Operators
import com.example.nothingc.scaledDp
import com.example.nothingc.scaledSp
import com.example.nothingc.ui.theme.Spacing.sm
import com.example.nothingc.ui.theme.Spacing.xs
import com.example.nothingc.ui.theme.appWideFont


@Composable
fun MenuComponent(modifier: Modifier) {
    var showPopUp by remember { mutableStateOf(false) } // -> STATE

    Box(
        modifier = modifier,
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { showPopUp = !showPopUp }
        ) {
            Icon(
                modifier = Modifier.size(35.scaledDp()),
                imageVector = Icons.Default.Menu,
                contentDescription = "Toggle drawer"
            )
        }
    }

    if(showPopUp){
        MenuPopup()
    }
}

@Composable
fun MenuPopup() {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Card(
                elevation = 5.dp,
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(0.6f)
                    .padding(end = sm.scaledDp())
                    .border(
                        2.dp,
                        color = MaterialTheme.colors.background,
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                ) {
                    val (sel1, sel2, sel3) = createRefs()

                    ClickableText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(sel1) {
                                top.linkTo(parent.top, margin = 16.dp)
                                end.linkTo(parent.end, margin = 8.dp)
                            }
                            .padding(end = sm.scaledDp(), top = xs.scaledDp())
                        ,
                        text = AnnotatedString("History"),
                        style = TextStyle(
                            fontFamily = appWideFont,
                            textAlign = TextAlign.End,
                            fontSize = 24.scaledSp(),
                            color = MaterialTheme.colors.onPrimary,
                        ),
                        onClick = {
                            Log.d(TAG, "Clicked History")
                        }
                    )

                    ClickableText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(sel2) {
                                top.linkTo(sel1.bottom, margin = 16.dp)
                                end.linkTo(parent.end, margin = 8.dp)
                            }
                            .padding(end = sm.scaledDp(), top = xs.scaledDp())
                        ,
                        text = AnnotatedString("Theme"),
                        style = TextStyle(
                            fontFamily = appWideFont,
                            textAlign = TextAlign.End,
                            fontSize = 24.scaledSp(),
                            color = MaterialTheme.colors.onPrimary,
                        ),
                        onClick = {
                            Log.d(TAG, "Clicked Theme")
                        }
                    )

                    ClickableText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .constrainAs(sel3) {
                                top.linkTo(sel2.bottom, margin = 16.dp)
                                end.linkTo(parent.end, margin = 8.dp)
                            }
                            .padding(end = sm.scaledDp(), top = xs.scaledDp())
                        ,
                        text = AnnotatedString("Info"),
                        style = TextStyle(
                            fontFamily = appWideFont,
                            textAlign = TextAlign.End,
                            fontSize = 24.scaledSp(),
                            color = MaterialTheme.colors.onPrimary,
                        ),
                        onClick = {
                            Log.d(TAG, "Clicked Info")
                        }
                    )

                }
            }
        }
    }
}

@Composable
fun ThemeSelector(modifier: Modifier){
    Column(
        modifier = modifier
    ) {
        Row() {
            Text(
                modifier = Modifier,
                text = "System",
                fontFamily = appWideFont,
                textAlign = TextAlign.Center,
                fontSize = 24.scaledSp(),
                color = MaterialTheme.colors.onBackground
            )
            RadioButton(
                selected = true,
                onClick = { /*TODO*/ })
        }
        Row() {
            Text(
                modifier = Modifier,
                text = "Dark",
                fontFamily = appWideFont,
                textAlign = TextAlign.Center,
                fontSize = 24.scaledSp(),
                color = MaterialTheme.colors.onBackground
            )
            RadioButton(
                selected = false,
                onClick = { /*TODO*/ })
        }
        Row() {
            Text(
                modifier = Modifier,
                text = "Light",
                fontFamily = appWideFont,
                textAlign = TextAlign.Center,
                fontSize = 24.scaledSp(),
                color = MaterialTheme.colors.onBackground
            )
            RadioButton(
                selected = false,
                onClick = { /*TODO*/ })
        }
    }
}