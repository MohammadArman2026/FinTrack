package uk.ac.tees.mad.fintrack.ui.features.add_edit

import uk.ac.tees.mad.fintrack.core.utils.CategoryList
import uk.ac.tees.mad.fintrack.domain.model.CategoryUI

data class AddTransactionUiState(
    val amount : String = "",
    val title : String = "",
    val category : String = "",
    val date : Long = System.currentTimeMillis(),
    val note : String = "",
    val type : String = "income",
    val isLoading : Boolean = false,
    val error :String? = null ,
){
    val categoryList: List<CategoryUI>
        get() = when (type) {
            "income" -> CategoryList.incomeList
            "expense" -> CategoryList.expenseList
            else -> emptyList()
        }

    val canSubmit : Boolean
        get() = amount.isNotEmpty() &&
                    title.isNotEmpty() &&
                    category.isNotEmpty()

}