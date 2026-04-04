package uk.ac.tees.mad.fintrack.ui.features.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.core.utils.getCategoryUI
import uk.ac.tees.mad.fintrack.domain.model.Transaction
import uk.ac.tees.mad.fintrack.ui.theme.Dimens
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun RecentTransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier
) {
    val categoryUI = getCategoryUI(transaction.category)

    Card(
        modifier = modifier.fillMaxWidth().padding(vertical = Dimens.spaceXS),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        ),
        shape = RoundedCornerShape(Dimens.cardRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.cardPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(Dimens.iconBox)
                        .background(
                            categoryUI.color.copy(alpha = 0.2f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = categoryUI.icon,
                        contentDescription = null,
                        tint = categoryUI.color,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.width(Dimens.spaceSM))
                Column {

                    Text(
                        text = transaction.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    val date = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(transaction.date))

                    Text(
                        text = "${transaction.category} • $date",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                }
            }

            Text(
                text = if (transaction.type == "income")
                    "+₹${transaction.amount}"
                else
                    "-₹${transaction.amount}",

                color = if (transaction.type == "income")
                    Color(0xFF10B981)
                else
                    Color(0xFFEF4444),

                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}