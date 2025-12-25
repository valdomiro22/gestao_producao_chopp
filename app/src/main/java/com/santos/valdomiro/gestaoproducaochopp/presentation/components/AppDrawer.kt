package com.santos.valdomiro.gestaoproducaochopp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.AppRoutes
import com.santos.valdomiro.gestaoproducaochopp.ui.theme.Dimens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    navController: NavHostController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    content: @Composable () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = navBackStackEntry?.destination?.route
    var tituloPagina by remember { mutableStateOf("Home") }

    // Lista de rotas que NÃO devem mostrar o drawer nem a top bar
    val rotasSemDrawer = setOf(
        AppRoutes.LOGIN,
        AppRoutes.CADASTRO,
        AppRoutes.RECUPERAR_SENHA
    )

    // Determina se a tela atual deve exibir drawer e top bar
    val mostrarDrawer = rotaAtual !in rotasSemDrawer

    // Força o drawer a fechar toda vez que entrar em uma tela que tem drawer
    LaunchedEffect(rotaAtual) {
        if (mostrarDrawer && drawerState.isOpen) {
            coroutineScope.launch {
                drawerState.close()
            }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = mostrarDrawer,
        drawerContent = {
            if (mostrarDrawer) {
                ModalDrawerSheet(modifier = Modifier.width(280.dp)) {
                    Text(
                        text = "Gestão Chopp",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    HorizontalDivider()

                    Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        selected = rotaAtual == AppRoutes.HOME,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            navController.navigate(AppRoutes.HOME) {
                                launchSingleTop = true
                                restoreState = true
                            }
                            tituloPagina = "Home"
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        label = { Text("Configurações") },
                        icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                        selected = rotaAtual == AppRoutes.CONFIGURACOES,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            navController.navigate(AppRoutes.CONFIGURACOES) {
                                launchSingleTop = true
                                restoreState = true
                            }
                            tituloPagina = "Configurações"
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (mostrarDrawer) {
                    TopAppBar(
                        title = { Text(tituloPagina) },
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch { drawerState.open() }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Abrir menu")
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}