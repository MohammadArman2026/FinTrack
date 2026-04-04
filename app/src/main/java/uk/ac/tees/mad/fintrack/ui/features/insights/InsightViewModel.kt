package uk.ac.tees.mad.fintrack.ui.features.insights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class InsightViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(InsightUiState())
    val uiState = _uiState.asStateFlow()


    init {
        observeInsights()
    }

    fun onFromDateClick() {
        _uiState.update {
            it.copy(isFromDatePickerOpen = true)
        }
    }

    fun onToDateClick() {
        _uiState.update {
            it.copy(isToDatePickerOpen = true)
        }
    }

    fun onFromDateDismiss() {
        _uiState.update {
            it.copy(
                isFromDatePickerOpen = false
            )
        }
    }

    fun onToDateDismiss() {
        _uiState.update {
            it.copy(
                isToDatePickerOpen = false
            )
        }
    }

    fun onFromDateConfirm(date: Long) {
        _uiState.update {
            it.copy(
                fromDate = date,
                isFromDatePickerOpen = false
            )
        }
    }

    fun onToDateConfirm(date: Long) {
        _uiState.update {
            it.copy(
                toDate = date,
                isToDatePickerOpen = false
            )
        }
        observeInsights()
    }

    fun observeInsights() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            transactionRepository.getCategorySplit(
                _uiState.value.fromDate,
                _uiState.value.toDate
            ).collect { categoryData ->
                val total = categoryData.sumOf { it.amount }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        categoryList = categoryData,
                        totalAmount = total
                    )
                }
            }
        }
    }
}