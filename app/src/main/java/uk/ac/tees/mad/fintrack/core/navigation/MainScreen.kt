package uk.ac.tees.mad.fintrack.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.ac.tees.mad.fintrack.core.ui.BottomAppBar
import uk.ac.tees.mad.fintrack.core.ui.TopAppBar
import uk.ac.tees.mad.fintrack.ui.features.add_edit.AddTransactionScreen
import uk.ac.tees.mad.fintrack.ui.features.add_edit.AddTransactionViewModel
import uk.ac.tees.mad.fintrack.ui.features.home.HomeScreen
import uk.ac.tees.mad.fintrack.ui.features.home.HomeViewModel
import uk.ac.tees.mad.fintrack.ui.features.transactions.TransactionScreen
import uk.ac.tees.mad.fintrack.ui.features.transactions.TransactionViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    currentRoute: String?,
    startDestination: String = ""
) {
     val showBottomBar =  currentRoute in listOf(
             NavigationRoutes.HOME.route ,
             NavigationRoutes.TRANSACTIONS.route ,
             NavigationRoutes.GOALS.route ,
             NavigationRoutes.INSIGHTS.route
     )

    Scaffold(modifier = modifier.fillMaxSize() ,
        topBar = {
            TopAppBar(
                navController = navController,
                currentRoute  = currentRoute?: NavigationRoutes.HOME.route
            )
        } ,
        floatingActionButton = {
            if(currentRoute == NavigationRoutes.HOME.route){
                FloatingActionButton(onClick = {navController.navigate(NavigationRoutes.ADD_EDIT.route)} ,
                    containerColor = MaterialTheme.colorScheme.primary) {
                    Icon(
                        imageVector = Icons.Default.Add ,
                        contentDescription = "add"
                    )
                }
            }
        } ,
        bottomBar = {
            if(showBottomBar) {
                BottomAppBar(
                    navController = navController,
                    currentRoute = currentRoute ?: NavigationRoutes.HOME.route
                )
            }
        }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(NavigationRoutes.HOME.route){
                val viewModel : HomeViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen( uiState = uiState)
            }

            composable(NavigationRoutes.GOALS.route){

            }

            composable(NavigationRoutes.ADD_EDIT.route){
                val viewModel : AddTransactionViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                AddTransactionScreen(uiState = uiState ,
                    onTitleChange = viewModel::titleChange ,
                    onNoteChange = viewModel::onNoteChange ,
                    onCategorySelected = viewModel::categoryChange ,
                    onDateSelected = viewModel::onDateChange ,
                    onTypeSelected = viewModel::onTypeChange ,
                    onAmountChange = viewModel::onAmountChange ,
                    onInsertClick = viewModel::onInsertClick
                )
            }

            composable(NavigationRoutes.TRANSACTIONS.route){
                val viewModel : TransactionViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                TransactionScreen(uiState = uiState ,
                    onFilterSelected = viewModel::onFilterSelected
                )
            }

            composable(NavigationRoutes.INSIGHTS.route){

            }
        }
    }
}


/**
 * we should pass only uiState and lambdas or functions from the composable{} block.
 * in this way of passing uiState and lambdas preview will not break and we don't need
 * to break screen into two composable.
 */