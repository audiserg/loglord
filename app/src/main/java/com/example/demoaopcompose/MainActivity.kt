package com.example.demoaopcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demoaopcompose.aspect.LogAOP
import com.example.demoaopcompose.ui.theme.DemoaopcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DemoaopcomposeTheme {
                // A surface container using the 'background' color from the theme
                Nav.navigator()
            }
        }
    }
}
// @LogAOP
object Nav {
    // @LogAOP
    lateinit var navController: NavHostController

    @Composable
    fun navigator() {
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = "first") {
            composable("first") { UI.mainPageOne("text vefore change ", navController) }
            composable("second") { UI.secondPage(/*...*/) }
            /*...*/
        }
    }
}
@LogAOP
object UI {
    @Composable
    // @LogAOP
    fun mainPageOne(text: String, navController: NavHostController) {
        var textlabel by remember { mutableStateOf(text) }

        mainContent(
            textlabel,
            { textlabel = "Text after change" }
        ) { navController.navigate("second") }
    }

    @Composable
    private fun mainContent(
        textlabel: String,
        b1ClickListener: () -> Unit,
        b2ClickListener: () -> Unit
    ) {
        // The answer is no. AspectJ cannot intercept read/write operations on local variables, only on members.
        var textlabel1 = textlabel
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = textlabel1)
            Button(onClick = b1ClickListener, modifier = Modifier.padding(all = 16.dp)) {
                Text("Change text on Label")
                textlabel1 = "serg"
            }
            Button(onClick = b2ClickListener, modifier = Modifier.padding(all = 16.dp)) {
                Text("Navigate via compose")
            }
        }
    }

    @Composable
    fun secondPage() {
        Scaffold {
            Surface {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SecondPage")
                }
            }
        }
    }
}
