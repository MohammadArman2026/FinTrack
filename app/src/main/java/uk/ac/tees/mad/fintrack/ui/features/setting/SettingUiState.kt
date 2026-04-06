package uk.ac.tees.mad.fintrack.ui.features.setting

data class SettingUiState(
    val isDarkMode : Boolean = false ,
    val isResetDialogOpen : Boolean = false ,
    val dailyLimit : String = "0.0",
    val isLoading : Boolean = false
)