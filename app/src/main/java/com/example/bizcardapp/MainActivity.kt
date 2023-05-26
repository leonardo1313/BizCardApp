package com.example.bizcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bizcardapp.ui.theme.BizCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    var buttonClickedState by remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier
        .fillMaxSize()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

        ) {
            Column(modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CreateProfileImage()
                Divider(thickness = 4.dp)
                CreateProfileInfo()
                Button(
                    onClick = {
                        buttonClickedState = !buttonClickedState
                    },
                    modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "Show portfolio",
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 18.sp)
                }
                if (buttonClickedState) {
                    PortfolioBox()
                } else {
                    Box {}
                }
            }

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PortfolioBox() {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary))
            {
            PortfolioContent(data = listOf("Mickey Mouse goes to Space", "Adventures of Mickey Mouse and Pluto", "Mickey Mouse: The Movie"))
        }
    }
}

@Composable
fun PortfolioContent(data: List<String>) {
    LazyColumn{
        items(data) { item ->
            Text(text = item)
        }
    }
}

@Composable
private fun CreateProfileInfo() {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mickey Mouse",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text = "Disney Studio Character",
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "email: mickeymouse@disney.com"
        )
    }
}

@Composable
private fun CreateProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(6.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.White),
        tonalElevation = 5.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),

        ) {
        Image(
            painter = painterResource(
                id = R.drawable.profile_image
            ),
            contentDescription = null,
            modifier = modifier
                .size(135.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    BizCardAppTheme {
        CreateBizCard()
    }
}