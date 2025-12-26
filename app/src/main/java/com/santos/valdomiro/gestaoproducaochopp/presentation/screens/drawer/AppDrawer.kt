import ads_mobile_sdk.h5
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen


@Composable
fun DrawerContent(
    selectedRoute: String,
    onItemClick: (Screen) -> Unit
) {
    val items = listOf(Screen.Home, Screen.Configuracoes)

    Column(
        modifier = Modifier
            .width(280.dp)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text("Meu App", color = Color.White, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(item.title) },
                selected = item.route == selectedRoute,
                onClick = { onItemClick(item) },
                icon = { item.icon?.let { Icon(it, contentDescription = null) } },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}