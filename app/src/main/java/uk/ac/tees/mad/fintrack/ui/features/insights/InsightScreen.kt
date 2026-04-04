package uk.ac.tees.mad.fintrack.ui.features.insights


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.ui.features.insights.component.AnalyticsHeader
import uk.ac.tees.mad.fintrack.ui.features.insights.component.CategorySplitCard
import uk.ac.tees.mad.fintrack.ui.features.insights.component.DatePicker
import uk.ac.tees.mad.fintrack.ui.features.insights.component.DateRangeSection


@Composable
fun InsightScreen(
    uiState: InsightUiState,
    onFromDateClick: () -> Unit,
    onToDateClick: () -> Unit,
    onFromDateDismiss: () -> Unit,
    onToDateDismiss: () -> Unit,
    onFromDateConfirm: (Long) -> Unit,
    onToDateConfirm: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {

        item {
            AnalyticsHeader()
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {

            if (uiState.isToDatePickerOpen){
                DatePicker(
                    onDismiss = onToDateDismiss,
                    onConfirm = onToDateConfirm
                )
            }

            if(uiState.isFromDatePickerOpen){
                DatePicker(
                    onDismiss = onFromDateDismiss,
                    onConfirm = onFromDateConfirm
                )
            }

            DateRangeSection(
                fromDate = uiState.fromDate,
                toDate = uiState.toDate,
                onFromClick = onFromDateClick,
                onToClick = onToDateClick
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            CategorySplitCard(
                total = uiState.totalAmount,
                categories = uiState.categoryList
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun InsightScreenPreview(){
    InsightScreen(
        uiState = InsightUiState(),
        onFromDateClick = {},
        onToDateClick = {},
        onFromDateDismiss = {},
        onToDateDismiss = {},
        onFromDateConfirm = {},
        onToDateConfirm = {}
    )
}

