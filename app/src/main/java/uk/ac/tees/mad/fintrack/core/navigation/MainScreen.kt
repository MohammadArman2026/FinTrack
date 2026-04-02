package uk.ac.tees.mad.fintrack.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentRoute: String?,
    startDestination: String = ""
) {
    Scaffold(modifier = modifier.fillMaxSize() ,
        topBar = {} ,
        bottomBar = {}) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(NavigationRoutes.HOME.route){

            }

            composable(NavigationRoutes.GOALS.route){

            }

            composable(NavigationRoutes.ADD_EDIT.route){

            }

            composable(NavigationRoutes.TRANSACTIONS.route){

            }

            composable(NavigationRoutes.INSIGHTS.route){

            }
        }
    }
}