package com.example.demoaopcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demoaopcompose.aspect.LogAOP
import com.example.demoaopcompose.ui.theme.DemoaopcomposeTheme
@LogAOP
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DemoaopcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    UI.Greeting("main") {
                        println("button clicked")
                    }
                }
            }
        }
    }
}

@LogAOP
object UI {
    @Composable
    fun Greeting(text: String, onclick: () -> Unit) {
        var textlabel by remember { mutableStateOf("Label Text before change") }

        mainPage(textlabel, { textlabel = "Text after change" })
    }
    @Composable
    private fun mainPage(textlabel: String, block: () -> Unit) {
        var textlabel1 = textlabel
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
            Text(text = textlabel1)
            Button(onClick = block, modifier = Modifier.padding(all = 16.dp)) {
                Text("Change text on Label")
            }
        }
    }
}
