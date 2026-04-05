package uk.ac.tees.mad.fintrack.ui.features.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.ui.theme.Dimens


@Composable
fun SettingScreen(
    isDarkMode: Boolean,
    onToggle: (Boolean) -> Unit,
    onResetClick: () -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    isLoading: Boolean,
    isDialogOpen: Boolean
) {
    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize() ,
            verticalArrangement = Arrangement.spacedBy(Dimens.spaceXS)) {
            ThemeSettingCard(
                modifier = Modifier.padding(top = Dimens.spaceXS),
                isDarkMode = isDarkMode,
                onToggle = onToggle
            )
            ResetCard(
                onResetClick = onResetClick,
            )
        }
        if(isDialogOpen){
            ResetConfirmationDialog(
                onConfirm = onConfirm ,
                onDismiss = onDismiss ,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp).align(Alignment.Center) ,
                color = MaterialTheme.colorScheme.primary  ,
            )
        }
    }
}


@Composable
fun ResetConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit ,
    modifier : Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Reset")
        },
        text = {
            Text(text = "Are you sure you want to reset?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("No")
            }
        }
    )
}



@Composable
fun ResetCard(onResetClick:()-> Unit ){
    Card(modifier = Modifier.fillMaxWidth() ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        ) ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ) ,
        shape = RoundedCornerShape(Dimens.cardRadius)
    ) {
        Row (modifier = Modifier.padding(Dimens.cardPadding)){
            Text(
                text = "Reset" ,
                style = MaterialTheme.typography.bodySmall ,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onResetClick) {
                Icon(
                    imageVector = Icons.Default.LockReset ,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun ThemeSettingCard(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean ,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(Dimens.cardRadius),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(Dimens.cardElevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.cardPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {
                when {
                    isDarkMode -> {

                        Text(
                            text = "Dark Mode",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Disable Dark Theme",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
                        )

                    }

                    else -> {
                        Text(
                            text = "Light Mode",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Enable Dark theme",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f)
                        )

                    }
                }
            }
            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    onToggle(it)
                } ,
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingScreen(
        isDarkMode = true,
        onToggle = {},
        onResetClick = {},
        onDismiss = {},
        onConfirm = {},
        isLoading = false,
        isDialogOpen = false
    )
}