package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
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

@Composable
fun MainScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        TopItem()
        CenterItem()
    }
}

@Composable
fun TopItem() {
    Row(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .height(100.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CenterItem(){

    var step = remember { mutableStateOf(0) }
    var stepCounter = CalcStep(step.value)
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
        .clickable
        {
            if (step.value > 4)
                step.value = 0
            else if(step.value in 1..3) step.value = (1..4).random()
            else ++step.value
        },
        horizontalAlignment = Alignment.CenterHorizontally){
        ImageItem(step.value)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = GetText(step.value), fontSize = 18.sp)
    }
}

@Composable
fun CalcStep(step: Int) : Int {

    var counter = step

    if (counter > 4)
        counter = 0
    else if(counter in 1..3) counter = (1..3).random()

    return counter
}

@Composable
fun ImageItem(step: Int) {
    Box(modifier = Modifier.background(Color(195, 236, 210), shape = RoundedCornerShape(10))
        .size(250.dp, 250.dp),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = GetImage(step),
            contentDescription = GetDescriptionText(step)
        )
    }
}

@Composable
fun GetImage(index: Int): Painter =
    when(index){
        0 -> painterResource(R.drawable.lemon_tree)
        in 1..3 -> painterResource(R.drawable.lemon_squeeze)
        4 -> painterResource(R.drawable.lemon_drink)
        5 -> painterResource(R.drawable.lemon_restart)
        else -> throw Exception("Неверный индекс")
    }

@Composable
fun GetText(index: Int): String =
    when(index){
        0 -> stringResource(R.string.lemon_tree)
        in 1..3 -> stringResource(R.string.lemon)
        4 -> stringResource(R.string.lemonade)
        5 -> stringResource(R.string.lemon_restart)
        else -> throw Exception("Неверный индекс")
    }

@Composable
fun GetDescriptionText(index: Int): String =
    when(index){
        0 -> stringResource(R.string.lemon_tree_description)
        in 1..3 -> stringResource(R.string.lemon_description)
        4 -> stringResource(R.string.lemonade_description)
        5 -> stringResource(R.string.lemon_restart_description)
        else -> throw Exception("Неверный индекс")
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        MainScreen()
    }
}