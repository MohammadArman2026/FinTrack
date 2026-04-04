package uk.ac.tees.mad.fintrack.ui.features.insights.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DateLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelMedium,
    )
}