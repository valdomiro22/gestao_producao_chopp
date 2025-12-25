package com.santos.valdomiro.gestaoproducaochopp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.AppNavigation
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.GestaoUsuarioFirebaseAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }

        setContent {
            GestaoUsuarioFirebaseAuthTheme {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController,
                    destino = viewModel.startDestination
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GestaoUsuarioFirebaseAuthTheme {
    }
}