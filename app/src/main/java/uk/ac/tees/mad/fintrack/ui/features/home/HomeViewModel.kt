package uk.ac.tees.mad.fintrack.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import uk.ac.tees.mad.fintrack.core.utils.toTransaction
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private  val transactionRepository: TransactionRepository) :
    ViewModel(){
        private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeHomeScreen()
    }

    private fun observeHomeScreen(){
        viewModelScope.launch {
            combine(
                transactionRepository.getTotalIncome() ,
                transactionRepository.getTotalExpense() ,
                transactionRepository.getTotalBalance() ,
                transactionRepository.getRecentTransaction()
            ){income , expense , balance , recent ->
                HomeUiState(
                    isLoading = false ,
                    totalIncome = income?:0.0 ,
                    totalBalance = balance?:0.0 ,
                    totalExpense = expense ?:0.0 ,
                    recentTransactions = recent
                )
            }.catch { e->
                _uiState.value = HomeUiState(
                    isLoading = false ,
                    error = e.message ?:"something went wrong"
                )
            }.collect{it->
                _uiState.value = it
            }
        }
    }
}