package uk.ac.tees.mad.fintrack.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController , currentRoute : String) {

    when (currentRoute) {

        "Home" -> {
            TopAppBar(
                title = { Text("Hello") } ,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings ,
                            modifier = Modifier.clickable{

                            },
                            contentDescription = "setting"
                        )
                    }
                }
            )
        }
        "Transactions" -> {
            TopAppBar(
                title = { Text("Transactions") } ,
                     actions = {
                         IconButton(onClick = {}) {
                             Icon(
                                 imageVector = Icons.Default.Settings ,
                                 contentDescription = "setting"
                             )
                         }
                }
            )
        }

        "Goals" -> {
            TopAppBar(
                title = { Text("Goals") } ,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings ,
                            contentDescription = "setting"
                        )
                    }
                }
            )
        }

        "Insights" -> {
            TopAppBar(
                title = { Text("Insights") } ,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings ,
                            contentDescription = "setting"
                        )
                    }
                }
            )
        }

        "AddEdit" -> {
            TopAppBar(
                title = { Text("New Transaction") }  ,
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
    }
}

