package uk.ac.tees.mad.fintrack.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.domain.model.Transaction
import uk.ac.tees.mad.fintrack.ui.features.home.component.BalanceCard
import uk.ac.tees.mad.fintrack.ui.features.home.component.ExpenseCard
import uk.ac.tees.mad.fintrack.ui.features.home.component.IncomeCard
import uk.ac.tees.mad.fintrack.ui.features.home.component.RecentTransaction

@Composable
fun HomeScreen(uiState: HomeUiState) {

    val dummyTransactions = listOf(

        Transaction(
            id = 1,
            title = "Starbucks",
            amount = 5.75,
            type = "expense",
            category = "Food & Dining",
            date = 10000,
            note = "Coffee"
        ),

        Transaction(
            id = 2,
            title = "Uber Ride",
            amount = 12.50,
            type = "expense",
            category = "Transport",
            date = 10000,
            note = "Office commute"
        ),

        Transaction(
            id = 3,
            title = "Salary",
            amount = 4200.00,
            type = "income",
            category = "Salary",
            date = 10000,
            note = "Monthly salary"
        ),

        Transaction(
            id = 4,
            title = "Amazon",
            amount = 250.00,
            type = "expense",
            category = "Shopping",
            date = 10000,
            note = "Headphones"
        ),

        Transaction(
            id = 5,
            title = "Netflix",
            amount = 15.00,
            type = "expense",
            category = "Entertainment",
            date = 1000000,
            note = "Subscription"
        ),

        Transaction(
            id = 6,
            title = "Freelance Project",
            amount = 800.00,
            type = "income",
            category = "Freelance",
            date = 100000,
            note = "Client payment"
        ),

        Transaction(
            id = 7,
            title = "Grocery Store",
            amount = 95.40,
            type = "expense",
            category = "Groceries",
            date = 100000,
            note = "Weekly groceries"
        ),

        Transaction(
            id = 8,
            title = "Doctor Visit",
            amount = 60.00,
            type = "expense",
            category = "Health",
            date = 100000,
            note = "Consultation"
        )
    )


    LazyColumn(modifier = Modifier.fillMaxSize() ,
            contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            BalanceCard(balance = uiState.totalBalance)
        }
        item{
            IncomeCard(income = uiState.totalIncome)
        }
        item {
            ExpenseCard(expense = uiState.totalExpense)
        }

        item {
            RecentTransaction(transactions = uiState.recentTransactions)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreen(
        uiState = HomeUiState()
    )
}