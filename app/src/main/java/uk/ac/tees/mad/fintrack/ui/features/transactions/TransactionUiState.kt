package uk.ac.tees.mad.fintrack.ui.features.transactions

import uk.ac.tees.mad.fintrack.domain.model.Transaction


data class TransactionUiState(
    val selectedFilter : TransactionFilter = TransactionFilter.All ,
    val transactions : List<Transaction> = emptyList() ,
    val isLoading : Boolean = false ,
    val error : String? = null
)

sealed class TransactionFilter{
    object All : TransactionFilter()
    data class CategoryFilter(val category : String) : TransactionFilter()
    data class TypeFilter(val type : String) : TransactionFilter()
}