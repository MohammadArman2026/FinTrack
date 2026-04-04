package uk.ac.tees.mad.fintrack.ui.features.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {
    private val selectedFilter = MutableStateFlow<TransactionFilter>(TransactionFilter.All)
    val uiState : StateFlow<TransactionUiState> = combine(
        transactionRepository.getAllTransactions(),
        selectedFilter
    ){list , filter ->
        val filteredList = when(filter){
            TransactionFilter.All -> list
            is TransactionFilter.CategoryFilter -> list.filter { it.category == filter.category }
            is TransactionFilter.TypeFilter -> list.filter { it.type == filter.type }
        }
        TransactionUiState(
            selectedFilter = filter,
            transactions = filteredList,
            isLoading = false
        )
    }.stateIn(
        viewModelScope ,
        SharingStarted.WhileSubscribed(5000),
        TransactionUiState()
    )

    fun onFilterSelected(filter: TransactionFilter) {
        selectedFilter.value = filter
    }

}