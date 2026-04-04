package uk.ac.tees.mad.fintrack.ui.features.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.ui.theme.Dimens

@Composable
fun IncomeCard(
    modifier: Modifier = Modifier,
    income: Double
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.cardRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        )
    ) {
        Column (modifier = Modifier.fillMaxWidth().padding(Dimens.cardPadding) ,
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceMD)){
            Row (modifier = Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(Dimens.iconBox)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDownward,
                        contentDescription = "income",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }

                Text(
                    text = "Total Income",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(start = Dimens.spaceMD)
                )
            }

            Text(
                text = "$ $income",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}