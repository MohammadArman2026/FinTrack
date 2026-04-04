package uk.ac.tees.mad.fintrack.ui.features.insights.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DateRangeSection(
    fromDate: Long,
    toDate: Long,
    onFromClick: () -> Unit,
    onToClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Column(modifier = Modifier.weight(1f)) {
            DateLabel("FROM")
            Spacer(modifier = Modifier.height(4.dp))
            DateCard(fromDate, onFromClick)
        }

        Column(modifier = Modifier.weight(1f)) {
            DateLabel("TO")
            Spacer(modifier = Modifier.height(4.dp))
            DateCard(toDate, onToClick)
        }
    }
}