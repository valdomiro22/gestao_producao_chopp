package com.santos.valdomiro.gestaoproducaochopp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.santos.valdomiro.gestaoproducaochopp.MainActivityViewModel
import com.santos.valdomiro.gestaoproducaochopp.presentation.alteraremail.AlterarEmailScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.alterarsenha.AlterarSenhaScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.cadastrarusuario.CadastrarUsuarioScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.configuracoes.ConfiguracoesUsuarioScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.home.HomeScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.login.LoginScreen
import com.santos.valdomiro.gestaoproducaochopp.presentation.recuperarsenha.RecuperarSenhaScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    destino: String,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = destino
    ) {
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                irParaCadastro = {
                    navController.navigate(AppRoutes.CADASTRO)
                },

                irParaHome = {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                },

                irParaRecuperarSenha = {
                    navController.navigate(AppRoutes.RECUPERAR_SENHA)
                }
            )
        }

        composable(AppRoutes.CADASTRO) {
            CadastrarUsuarioScreen(
                irParaLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.HOME) {
            HomeScreen(
                irParaLogin = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.HOME) { inclusive = true }
                    }
                },

                irParaConfiguracoes = {
                    navController.navigate(AppRoutes.CONFIGURACOES)
                }
            )
        }

        composable(AppRoutes.CONFIGURACOES) {
            ConfiguracoesUsuarioScreen(
                irParaHome = {
                    navController.popBackStack()
                },

                irParaAlterarEmail = {
                    navController.navigate(AppRoutes.ALTERAR_EMAIL)
                },

                irParaAlterarSenha = {
                    navController.navigate(AppRoutes.ALTERAR_SENHA)
                },

                irParaLogin = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }

        composable(AppRoutes.ALTERAR_EMAIL) {
            AlterarEmailScreen(
                irParaConfiguracoes = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.ALTERAR_SENHA) {
            AlterarSenhaScreen(
                irParaConfiguracoes = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.RECUPERAR_SENHA) {
            RecuperarSenhaScreen(
                irParaLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}