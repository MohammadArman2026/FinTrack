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
fun TransactionScreen(uiState: TransactionUiState, onFilterSelected: (TransactionFilter) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.screenPadding),
        contentPadding = PaddingValues(vertical = Dimens.spaceXS),
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceXS)
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

//        val mockTransactions = listOf(
//            Transaction(
//                id = 1,
//                title = "Rent",
//                amount = 1200.0,
//                type = "expense",
//                category = "Housing",
//                date = 100000,
//                note = "Monthly rent"
//            ),
//            Transaction(
//                id = 2,
//                title = "Amazon",
//                amount = 45.99,
//                type = "expense",
//                category = "Shopping",
//                date = 10000,
//                note = "Bought headphones"
//            ),
//            Transaction(
//                id = 3,
//                title = "Dividend",
//                amount = 12.50,
//                type = "income",
//                category = "Investment",
//                date = 100000,
//                note = null
//            ),
//            Transaction(
//                id = 4,
//                title = "Gas Station",
//                amount = 60.0,
//                type = "expense",
//                category = "Transport",
//                date = 10000,
//                note = ""
//            ),
//            Transaction(
//                id = 5,
//                title = "Netflix",
//                amount = 15.99,
//                type = "expense",
//                category = "Entertainment",
//                date = 100000,
//                note = "Monthly subscription"
//            )
//        )

        items(
            uiState.transactions,
            key = { it.id }
        ) { item ->
            TransactionItemCard(
                transaction = item,
                onDeleteClick = {}
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TransactionScreenPreview() {
    TransactionScreen(
        uiState = TransactionUiState(),
        onFilterSelected = {}
    )
}