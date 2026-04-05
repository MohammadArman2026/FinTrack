package uk.ac.tees.mad.fintrack.ui.features.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.fintrack.core.utils.PreferenceManager
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val preferenceManager: PreferenceManager ,
    private val transactionRepository: TransactionRepository)
    : ViewModel() {

        private val _uiState : MutableStateFlow<SettingUiState> = MutableStateFlow(SettingUiState())
    val uiState = _uiState.asStateFlow()


    init {
        _uiState.update {
            it.copy(
                isDarkMode = preferenceManager.isDarkModeEnabled()
            )
        }
    }


    fun onToggle(value : Boolean){
        preferenceManager.setDarkMode(value)
        _uiState.update {
            it.copy(
                isDarkMode =value
            )
        }
    }

    fun onResetClick(){
        _uiState.update {
            it.copy(
                isResetDialogOpen = true
            )
        }
    }

    fun onDismiss(){
        _uiState.update {
            it.copy(
                isResetDialogOpen = false
            )
        }
    }

    fun onConfirmReset(){
        _uiState.update {
            it.copy(
                isResetDialogOpen = false
            )
        }

        viewModelScope.launch{
            _uiState.update {
                it.copy(
                    isResetDialogOpen = false ,
                    isLoading = true
                )
            }

      val result = withContext(Dispatchers.IO){
                transactionRepository.resetData()
            }

            result.onSuccess {
                _uiState.update {
                    it.copy(
                        isLoading = false ,
                    )
                }
            }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }

}