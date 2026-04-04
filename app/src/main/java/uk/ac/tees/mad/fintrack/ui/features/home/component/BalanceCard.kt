package uk.ac.tees.mad.fintrack.ui.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.fintrack.ui.theme.Dimens

@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    balance: Double
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.cardRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.cardPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXS)
        ) {
            Text(
                text = "TOTAL BALANCE",
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$$balance",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}