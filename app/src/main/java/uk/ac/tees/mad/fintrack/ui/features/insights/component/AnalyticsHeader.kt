package uk.ac.tees.mad.fintrack.ui.features.insights.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AnalyticsHeader() {
    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "FINANCIAL OVERVIEW",
            style = MaterialTheme.typography.labelMedium,
        )

        Text(
            text = "Analytics",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
    }
}