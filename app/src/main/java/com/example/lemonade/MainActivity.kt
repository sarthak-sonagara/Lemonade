package com.example.lemonade

import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.R
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
                    LemonadeApp(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Title(modifier = modifier)
        ImageAndText(modifier = modifier)
    }
}

@Composable
fun Title(modifier: Modifier = Modifier){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.Yellow
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(id = R.string.title),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ImageAndText(modifier: Modifier = Modifier){
    var pageNumber by remember {mutableStateOf(0)}
    var squeezeCount by remember { mutableStateOf(2) }
    val image = when(pageNumber){
        0 -> painterResource(id = R.drawable.lemon_tree)
        1 -> painterResource(id = R.drawable.lemon_squeeze)
        2 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    val imageDescription = when(pageNumber){
        0 -> stringResource(id = R.string.lemon_tree)
        1 -> stringResource(id = R.string.lemon_squeeze)
        2 -> stringResource(id = R.string.lemon_drink)
        else -> stringResource(id = R.string.lemon_restart)
    }
    val description = when(pageNumber){
        0 -> stringResource(id = R.string.lemon_tree_description)
        1 -> stringResource(id = R.string.lemon_squeeze_description)
        2 -> stringResource(id = R.string.lemon_drink_description)
        else -> stringResource(id = R.string.lemon_restart_description)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(204,255,229)
            ),
            shape = RoundedCornerShape(32.dp),
            modifier = Modifier.border(
                width = 2.dp,
                color = Color(105,2-5,216),
                shape = RoundedCornerShape(32.dp)
            ),
            onClick = {
                if(pageNumber == 1 && squeezeCount>0){
                    squeezeCount = squeezeCount - 1
                }
                else{
                    if(pageNumber == 0){
                        squeezeCount = (2..4).random()
                    }
                    pageNumber = (pageNumber + 1) % 4
                }
            }
        ) {
            Image(
                painter = image,
                contentDescription = imageDescription
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            fontSize = 20.sp,
        )
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp(modifier = Modifier)
    }
}