package uk.ac.tees.mad.fintrack.ui.features.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.domain.model.Transaction

@Composable
fun RecentTransaction(transactions: List<Transaction>){
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        transactions.take(5).forEach { transaction ->
            RecentTransactionItem(transaction)
        }
    }
}