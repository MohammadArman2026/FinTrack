package uk.ac.tees.mad.fintrack.ui.features.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.fintrack.ui.features.transactions.component.FilterChipsRow
import uk.ac.tees.mad.fintrack.ui.features.transactions.component.TransactionItemCard
import uk.ac.tees.mad.fintrack.ui.theme.Dimens

@Composable
fun TransactionScreen(uiState: TransactionUiState,
                      onFilterSelected: (TransactionFilter) -> Unit ,
                      onRemoveClick:(Int)-> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.screenPadding),
        contentPadding = PaddingValues(vertical = Dimens.spaceXS),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceMD)
    ) {
        stickyHeader {
            FilterChipsRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.spaceXS)
                    .background(MaterialTheme.colorScheme.background),
                filter = uiState.selectedFilter,
                onFilterSelected = onFilterSelected
            )
        }
        items(
            uiState.transactions,
            key = { it.id }
        ) { item ->
            TransactionItemCard(
                transaction = item,
                onDeleteClick = onRemoveClick
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TransactionScreenPreview() {
    TransactionScreen(
        uiState = TransactionUiState(),
        onFilterSelected = {} ,
        onRemoveClick = {}
    )
}