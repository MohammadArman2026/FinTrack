package uk.ac.tees.mad.fintrack.ui.features.insights

import uk.ac.tees.mad.fintrack.domain.model.CategoryData

data class InsightUiState(
    val fromDate: Long = System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000L ,
    val toDate : Long = System.currentTimeMillis() ,
    val isFromDatePickerOpen : Boolean = false ,
    val isToDatePickerOpen : Boolean = false ,
    val categoryList: List<CategoryData> = emptyList() ,
    val totalAmount : Double = 0.0 ,
    val isLoading : Boolean = false ,
    val error : String ? = null
)