package com.santos.valdomiro.gestaoproducaochopp.presentation.navigation
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

// Isso define o "contêiner" que vai segurar o seu navController
val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavHostController provided")
}