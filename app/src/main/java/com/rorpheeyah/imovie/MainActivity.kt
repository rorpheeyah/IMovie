package com.rorpheeyah.imovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rorpheeyah.imovie.ui.theme.IMovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppToolbar()

    }
}

@Composable
fun AppToolbar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Cyan)
            .padding(PaddingValues(16.dp, 8.dp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
        ) {

        Text(text = "$300")
        DropDownDemo()
        Image(
            modifier = Modifier
                .size(20.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {},
                ),
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentScale = ContentScale.Inside,
            contentDescription = "",
        )
    }
}

@Composable
fun DisplayUserSpinner(){
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    var selectedIndex by remember { mutableStateOf(0) }
    var context = LocalContext.current
}

@Composable
fun DropDownDemo(){
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }

    Box(modifier = Modifier
        .wrapContentSize(Alignment.TopCenter)
        .padding(8.dp)
    ){
        Text(
            items[selectedIndex],
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.White)
                .clickable(onClick = { expanded = true })
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Red)
        ){
            items.forEachIndexed{ index, s ->
                DropdownMenuItem(
                    text = {
                           val disabledText = if(s == disabledValue){
                               " (Disabled)"
                           }else{
                               ""
                           }
                        Text(text = s + disabledText)
                    },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    }
                )
            }
        }
    }
}

