package com.example.compose_pathway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_pathway.ui.theme.ComposepathwayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposepathwayTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){

    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    }else{
        Greetings()
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }

    val extraPadding = if(expanded) 48.dp else 0.dp

    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)){
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(onClick = { expanded = !expanded }) {
                Text(if(expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
private fun Greetings(names: List<String> = listOf("World","Compose")){
    Column() {
        for(name in names){
            Greeting(name = name)
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked:() -> Unit){

    Surface() {
      Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
      ) {
          Text("Welcome ot the Basics Codelab!")
          Button(
              modifier = Modifier.padding(vertical = 24.dp),
              onClick = onContinueClicked
          ){
              Text("Continue")
          }
      }
    }

}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposepathwayTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview(){
    ComposepathwayTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}