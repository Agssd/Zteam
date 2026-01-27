package com.example.zteam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.zteam.navigation.AppNavHost
import com.example.zteam.ui.theme.ZteamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZteamTheme(darkTheme = true) {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}