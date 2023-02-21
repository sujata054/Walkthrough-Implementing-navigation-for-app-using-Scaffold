package com.example.walkthrough_scaffold_and_navigation_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationusingscaffold.ui.theme.NavigationUsingScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           NavigationUsingScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

@Composable
fun ScaffoldApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Setting"){
            SettingsScreen(navController)
        }
    }
}
@Composable
fun MainTopBar(title: String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title)},
        actions = {
            IconButton(
                onClick = { expanded = !expanded}
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false}) {
                DropdownMenuItem(onClick = { navController.navigate("info")}) {
                    Text(text = "Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("setting") }) {
                    Text(text = "Setting")
                }
            }
        }
    )
}
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = { MainTopBar("My App", navController) },
        content = {Text(text = "Content for Home screen")},
    )
}
@Composable
fun InfoScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        content = {Text(text = "Content for Info screen")},
    )
}
@Composable
fun SettingsScreen(navController: NavController){
    Scaffold(
        topBar = { ScreenTopBar("Setting", navController) },
        content = {Text(text = "Content for Setting screen")},
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationUsingScaffoldTheme {
        ScaffoldApp()
    }
}