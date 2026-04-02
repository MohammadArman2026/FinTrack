package uk.ac.tees.mad.fintrack.core.navigation

sealed class NavigationRoutes(val route: String) {
    object HOME : NavigationRoutes("Home")
    object TRANSACTIONS : NavigationRoutes("Transactions")
    object GOALS : NavigationRoutes("Goals")
    object INSIGHTS : NavigationRoutes("Insights")
    object ADD_EDIT : NavigationRoutes("AddEdit")
}