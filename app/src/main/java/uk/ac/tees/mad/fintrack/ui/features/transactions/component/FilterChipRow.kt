package uk.ac.tees.mad.fintrack.ui.features.transactions.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.core.utils.CategoryList
import uk.ac.tees.mad.fintrack.ui.features.transactions.TransactionFilter

@Composable
fun FilterChipsRow(
    filter : TransactionFilter ,
    onFilterSelected : (TransactionFilter) -> Unit ,
    modifier: Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        FilterChip(
            text = "All",
            selected = filter is TransactionFilter.All,
            onFilterSelected = {onFilterSelected(TransactionFilter.All)},
        )

        CategoryFilterChip(
            filter = filter,
            isSelected = filter is TransactionFilter.CategoryFilter,
            onFilterSelected = onFilterSelected,
        )

        TypeFilterChip(
            filter = filter,
            onFilterSelected = onFilterSelected,
            isFilterSelected = filter is TransactionFilter.TypeFilter
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoryFilterChip(
    filter: TransactionFilter,
    isSelected : Boolean ,
    onFilterSelected: (TransactionFilter) -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }
    val selectedCategory =
        (filter as? TransactionFilter.CategoryFilter)?.category

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.widthIn(min = 110.dp , max = 120.dp)
    ) {
        Row(
            modifier = Modifier
                .menuAnchor()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (isSelected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text =  selectedCategory?:"Category",
                color = if (isSelected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.width(4.dp))

            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = if (isSelected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurface
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {

            CategoryList.categories.forEach { category ->

                DropdownMenuItem(
                    text = { Text(category.name) },
                    onClick = {
                        onFilterSelected(
                            TransactionFilter.CategoryFilter(category.name)
                        )
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeFilterChip(
    filter: TransactionFilter ,
    onFilterSelected: (TransactionFilter) -> Unit ,
    isFilterSelected : Boolean ,
){
    var expanded by remember {
        mutableStateOf(false)
    }

    val selectedType =
        (filter as? TransactionFilter.TypeFilter)?.type

    ExposedDropdownMenuBox(
        expanded = expanded ,
        onExpandedChange = {expanded = !expanded} ,
        modifier = Modifier.widthIn(min = 110.dp , max = 120.dp)
    ) {


        Row(
            modifier = Modifier
                .menuAnchor()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (isFilterSelected)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = selectedType ?: "Type",
                color = if (isFilterSelected)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.width(4.dp))

            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = if (selectedType!= null)
                    MaterialTheme.colorScheme.onPrimary
                else
                    MaterialTheme.colorScheme.onSurface
            )
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
        ) {
            CategoryList.typeList.forEach {type->
                DropdownMenuItem(
                    text = { Text(type) },
                    onClick = {
                        onFilterSelected(TransactionFilter.TypeFilter(type))
                        expanded = false
                    }
                )
            }
        }
    }
}