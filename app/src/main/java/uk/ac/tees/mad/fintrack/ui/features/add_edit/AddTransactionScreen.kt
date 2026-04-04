package uk.ac.tees.mad.fintrack.ui.features.add_edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.ui.features.add_edit.component.AmountInputField
import uk.ac.tees.mad.fintrack.ui.features.add_edit.component.CategoryDropdownField
import uk.ac.tees.mad.fintrack.ui.features.add_edit.component.DatePickerField
import uk.ac.tees.mad.fintrack.ui.features.add_edit.component.NoteField
import uk.ac.tees.mad.fintrack.ui.features.add_edit.component.TitleField
import uk.ac.tees.mad.fintrack.ui.features.add_edit.component.TypeToggle
import uk.ac.tees.mad.fintrack.ui.theme.Dimens

@Composable
fun AddTransactionScreen(
    uiState: AddTransactionUiState,
    onTitleChange: (String) -> Unit,
    onNoteChange: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onTypeSelected: (String) -> Unit,
    onDateSelected: (Long) -> Unit,
    onAmountChange: (String) -> Unit,
    onInsertClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = Dimens.screenPadding)) {
        Column(
            modifier = Modifier
                .fillMaxSize() ,
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceMD)
        ) {
            AmountInputField(
                amount = uiState.amount,
                onAmountChange = onAmountChange,
                type = uiState.type
            )
            TypeToggle(
                selected = uiState.type,
                onSelect = onTypeSelected
            )
            TitleField(
                title = uiState.title ,
                onTitleChange = onTitleChange
            )
            CategoryDropdownField(
                selectedCategory = uiState.category,
                onCategorySelected = onCategorySelected ,
                categoryList = uiState.categoryList
            )
            DatePickerField(
                selectedDate = uiState.date,
                onDateSelected = onDateSelected
            )
            NoteField(
                note = uiState.note,
                onNoteChange = onNoteChange
            )
        }
        Button(
            onClick = onInsertClick,
            enabled = uiState.canSubmit  && !uiState.isLoading,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(Dimens.cardRadius)
        ) {
            when{
                uiState.isLoading ->{
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp) ,
                        color = MaterialTheme.colorScheme.primary
                    )
                }else->{
                Text("Add Transaction")
                }
            }
        }

        if(uiState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center).size(48.dp)  ,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AddTransactionPreview(){
    AddTransactionScreen(
        uiState = AddTransactionUiState(),
        onTitleChange = {},
        onNoteChange = {},
        onCategorySelected = {},
        onTypeSelected = {},
        onDateSelected = {},
        onAmountChange = {},
        onInsertClick = {}
    )
}