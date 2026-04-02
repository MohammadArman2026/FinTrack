package uk.ac.tees.mad.fintrack.core.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController , currentRoute : String) {

    when (currentRoute) {

        "home" -> {
            TopAppBar(
                title = { Text("Hello") } ,

            )
        }

        "transactions" -> {
            TopAppBar(
                title = { Text("Transactions") }
            )
        }

        "goals" -> {
            TopAppBar(
                title = { Text("Goals") }
            )
        }

        "insights" -> {
            TopAppBar(
                title = { Text("Insights") }  ,

            )
        }

    }
}

