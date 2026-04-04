package uk.ac.tees.mad.fintrack.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.BarChart
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
fun BottomAppBar(navController: NavController , currentRoute : String) {

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
                        imageVector = when (screen.route) {
                            "Home" -> Icons.Default.Home
                            "Transactions" -> Icons.Default.List
                            "Goals" -> Icons.Default.Star
                            "Insights" -> Icons.Default.BarChart
                            else -> {
                                Icons.Default.Home
                            }
                        },
                        contentDescription = screen.route
                    )
                },
                label = {
                    Text(
                        text = screen.route
                    )
                }
            )
        }
    }
}