package uk.ac.tees.mad.fintrack.core.utils

import uk.ac.tees.mad.fintrack.data.local.TransactionEntity
import uk.ac.tees.mad.fintrack.domain.model.CategoryUI
import uk.ac.tees.mad.fintrack.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun getCategoryUI(category: String): CategoryUI {
    return CategoryList.categories.find { it.name == category }
        ?: CategoryList.categories.last()
}

fun Transaction.toEntity(): TransactionEntity{
    return TransactionEntity(
        title = title,
        amount = amount?:0.0,
        type = type,
        category = category,
        date = date ,
        note = note
    )
}


fun TransactionEntity.toTransaction(): Transaction{
    return Transaction(
        id = id,
        title = title,
        amount = amount,
        type = type,
        category = category,
        date = date,
        note = note
    )
}

fun formatMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}