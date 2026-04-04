package uk.ac.tees.mad.fintrack.ui.features.home

import uk.ac.tees.mad.fintrack.domain.model.Transaction

data class HomeUiState(
    val isLoading : Boolean = true ,
    val totalIncome : Double = 0.0 ,
    val totalExpense : Double = 0.0 ,
    val totalBalance : Double = 0.0 ,
    val recentTransactions : List<Transaction> = emptyList() ,
    val error: String ? = null
)