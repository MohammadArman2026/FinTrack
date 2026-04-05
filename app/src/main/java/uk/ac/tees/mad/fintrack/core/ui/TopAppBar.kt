package uk.ac.tees.mad.fintrack.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import uk.ac.tees.mad.fintrack.core.navigation.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController , currentRoute : String) {

    when (currentRoute) {

        "Home" -> {
            TopAppBar(
                title = { Text("Hello" ,
                    style = MaterialTheme.typography.titleMedium) } ,
                actions = {
                    ActionIcon {
                        navController.navigate(NavigationRoutes.SETTING.route)
                    }
                }
            )
        }
        "Transactions" -> {
            TopAppBar(
                title = { Text("Transactions" ,
                    style = MaterialTheme.typography.titleMedium) } ,
                     actions = {
                         ActionIcon {
                             navController.navigate(NavigationRoutes.SETTING.route)
                         }
                }
            )
        }

        "Goals" -> {
            TopAppBar(
                title = { Text("Goals" ,
                    style = MaterialTheme.typography.titleMedium) } ,
                actions = {
                    ActionIcon {
                        navController.navigate(NavigationRoutes.SETTING.route)
                    }
                }
            )
        }

        "Insights" -> {
            TopAppBar(
                title = { Text("Insights" ,
                    style = MaterialTheme.typography.titleMedium) } ,
                actions = {
                    ActionIcon {
                        navController.navigate(NavigationRoutes.SETTING.route)
                    }
                }
            )
        }

        "AddEdit" -> {
            TopAppBar(
                title = { Text("New Transaction" ,
                    style = MaterialTheme.typography.titleMedium) }  ,
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Default.Close ,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        }

        "Setting" ->{
            TopAppBar(
                title = {Text(text = "Setting" ,
                    style = MaterialTheme.typography.titleMedium)} ,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ActionIcon(onClick:()-> Unit){
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Settings ,
            contentDescription = "setting"
        )
    }
}

