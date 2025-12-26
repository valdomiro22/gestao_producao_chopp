package com.santos.valdomiro.gestaoproducaochopp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String = "",
    val icon: ImageVector? = null
) {
    // Telas que NÃO aparecem no Drawer
    object Login : Screen("login")
    object Cadastro : Screen("cadastro")
    object Splash : Screen("splash")

    // Telas que APARECEM no Drawer (possuem Título e Ícone)
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Configuracoes : Screen("configuracoes", "Configurações", Icons.Default.Settings)
    object AlterarSenha : Screen("alterar-senha", "Alterar Senha", Icons.Default.Lock)
    object AlterarEmail : Screen("alterar-email", "Alterar Email", Icons.Default.Email)
    object RecuperarSenha : Screen("recuperar-senha", "Recuperar Senha", Icons.Default.Refresh)
}

// Uma lista apenas com o que deve aparecer no menu lateral
val drawerScreens = listOf(
    Screen.Home,
    Screen.Configuracoes,
    Screen.AlterarSenha
)