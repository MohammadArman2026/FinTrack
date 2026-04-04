package uk.ac.tees.mad.fintrack.ui.features.add_edit.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TypeToggle(
    selected: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(4.dp)
    ) {

        ToggleButton(
            modifier = Modifier.weight(1f),
            text = "Expense",
            selected = selected == "expense",
            color = MaterialTheme.colorScheme.primary
        ) { onSelect("expense") }

        ToggleButton(
            modifier = Modifier.weight(1f),
            text = "Income",
            selected = selected == "income",
            color = MaterialTheme.colorScheme.primary
        ) { onSelect("income") }
    }
}

@Composable
fun ToggleButton(
    modifier: Modifier ,
    text: String,
    selected: Boolean,
    color: Color,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(if (selected) color else Color.Transparent)
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) MaterialTheme.colorScheme.onPrimary else Color.Black
        )
    }
}