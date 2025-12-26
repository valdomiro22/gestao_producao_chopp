import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen

@Composable
fun DrawerMenuContent(
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Gestão de Chopp", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        // Item Home
        NavigationDrawerItem(
            label = { Text("Início") },
            selected = currentRoute == Screen.Home.route,
            onClick = { onItemClick(Screen.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) }
        )

        // Item Configurações
        NavigationDrawerItem(
            label = { Text("Configurações") },
            selected = currentRoute == Screen.Configuracoes.route,
            onClick = { onItemClick(Screen.Configuracoes.route) },
            icon = { Icon(Icons.Default.Settings, contentDescription = null) }
        )
    }
}