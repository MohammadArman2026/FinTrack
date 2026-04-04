package uk.ac.tees.mad.fintrack.ui.features.add_edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.fintrack.domain.model.Transaction
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Inject


@HiltViewModel
class AddTransactionViewModel @Inject constructor(private val transactionRepository: TransactionRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState = _uiState.asStateFlow()

    fun titleChange(title: String) {
        _uiState.update {
            it.copy(
                title = title
            )
        }
    }

    fun onNoteChange(note: String) {
        _uiState.update {
            it.copy(
                note = note
            )
        }
    }

    fun categoryChange(category: String) {
        _uiState.update {
            it.copy(
                category = category,
            )
        }
    }

    fun onDateChange(date: Long) {
        _uiState.update {
            it.copy(
                date = date
            )
        }
    }

    fun onTypeChange(type: String) {
        if (type != _uiState.value.type) {
            _uiState.update {
                it.copy(
                    type = type,
                    category = ""
                )
            }
        }
    }

    fun onAmountChange(amount: String) {
        _uiState.update {
            it.copy(
                amount = amount
            )
        }
    }

    fun onInsertClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val transaction = Transaction(
                id = 0,
                title = _uiState.value.title,
                amount = _uiState.value.amount.toDoubleOrNull(),
                type = _uiState.value.type,
                category = _uiState.value.category,
                date = _uiState.value.date,
                note = _uiState.value.note
            )
            transactionRepository
                .insertTransaction(transaction)
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
                .onSuccess {
                   reset()
                }
        }
    }

    private fun reset(){
        _uiState.update {
            AddTransactionUiState()
        }
    }

}