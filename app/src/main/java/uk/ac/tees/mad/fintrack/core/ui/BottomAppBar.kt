package uk.ac.tees.mad.fintrack.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import uk.ac.tees.mad.fintrack.core.navigation.NavigationRoutes
import java.util.Locale

@Composable
fun AppBottomBar(navController: NavController , currentRoute : String) {

    NavigationBar {

        listOf(
            NavigationRoutes.HOME,
            NavigationRoutes.TRANSACTIONS,
            NavigationRoutes.GOALS,
            NavigationRoutes.INSIGHTS,
        ).forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(NavigationRoutes.HOME.route)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = when (screen) {
                            NavigationRoutes.HOME -> Icons.Default.Home
                           NavigationRoutes.TRANSACTIONS -> Icons.Default.List
                          NavigationRoutes.GOALS -> Icons.Default.Star
                            NavigationRoutes.INSIGHTS -> Icons.Default.BarChart
                            else -> {}
                        },
                        contentDescription = screen.route
                    )
                },
                label = {
                    Text(replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() })
                }
            )
        }
    }
}