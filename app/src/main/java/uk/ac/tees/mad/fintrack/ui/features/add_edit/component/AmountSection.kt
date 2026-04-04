package uk.ac.tees.mad.fintrack.ui.features.add_edit.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.fintrack.ui.theme.Dimens


@Composable
fun AmountInputField(
    amount: String,
    onAmountChange: (String) -> Unit,
    type: String // "income" or "expense"
) {

    val textColor = if(type == "income"){
        MaterialTheme.colorScheme.secondary
    }else{
        MaterialTheme.colorScheme.error
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.spaceLG) ,
        verticalArrangement = Arrangement.spacedBy(Dimens.spaceMD)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "$",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Spacer(modifier = Modifier.width(12.dp))

            BasicTextField(
                value = amount,
                onValueChange = { input ->

                    if (input.matches(Regex("^\\d*\\.?\\d*\$"))) {
                        onAmountChange(input)
                    }
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                decorationBox = { innerTextField ->

                    if (amount.isEmpty()) {
                        Text(
                            text = "0.00",
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}