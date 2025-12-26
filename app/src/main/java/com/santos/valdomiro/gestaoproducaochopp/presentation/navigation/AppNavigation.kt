package com.santos.valdomiro.gestaoproducaochopp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.alteraremail.AlterarEmailScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.alterarsenha.AlterarSenhaScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.cadastrarusuario.CadastrarUsuarioScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.configuracoes.ConfiguracoesUsuarioScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.home.HomeScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.screens.login.LoginScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable(Screen.Login.route) { LoginScreen() }
            composable(Screen.Cadastro.route) { CadastrarUsuarioScreen() }
            composable(Screen.Home.route) { HomeScreen() }
            composable(Screen.Configuracoes.route) { ConfiguracoesUsuarioScreen() }
            composable(Screen.AlterarEmail.route) { AlterarEmailScreen() }
            composable(Screen.AlterarSenha.route) { AlterarSenhaScreen() }
        }
    }
}