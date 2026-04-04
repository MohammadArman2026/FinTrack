package uk.ac.tees.mad.fintrack.core.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.Color
import uk.ac.tees.mad.fintrack.domain.model.CategoryUI

object CategoryList {

    val incomeList = listOf(
        CategoryUI("Salary", Icons.Default.AttachMoney, Color(0xFF10B981), "income"),
        CategoryUI("Freelance", Icons.Default.Work, Color(0xFF00BCD4), "income"),
        CategoryUI("Investment", Icons.Default.ShowChart, Color(0xFF4CAF50), "income"),
        CategoryUI("Gift", Icons.Default.CardGiftcard, Color(0xFFFFC107), "income"),
    )

    val expenseList = listOf(
        CategoryUI("Food & Dining", Icons.Default.Restaurant, Color(0xFF4CAF50), "expense"),
        CategoryUI("Groceries", Icons.Default.ShoppingCart, Color(0xFF8BC34A), "expense"),
        CategoryUI("Transport", Icons.Default.DirectionsCar, Color(0xFF2196F3), "expense"),
        CategoryUI("Shopping", Icons.Default.ShoppingBag, Color(0xFF9C27B0), "expense"),
        CategoryUI("Entertainment", Icons.Default.Movie, Color(0xFFFF9800), "expense"),
        CategoryUI("Bills", Icons.Default.Receipt, Color(0xFF795548), "expense"),
        CategoryUI("Health", Icons.Default.LocalHospital, Color(0xFFE91E63), "expense"),
        CategoryUI("Other", Icons.Default.Category, Color.Gray, "expense")
        )

    val categories = listOf(

        // EXPENSE
        CategoryUI("Food & Dining", Icons.Default.Restaurant, Color(0xFF4CAF50), "expense"),
        CategoryUI("Groceries", Icons.Default.ShoppingCart, Color(0xFF8BC34A), "expense"),
        CategoryUI("Transport", Icons.Default.DirectionsCar, Color(0xFF2196F3), "expense"),
        CategoryUI("Shopping", Icons.Default.ShoppingBag, Color(0xFF9C27B0), "expense"),
        CategoryUI("Entertainment", Icons.Default.Movie, Color(0xFFFF9800), "expense"),
        CategoryUI("Bills", Icons.Default.Receipt, Color(0xFF795548), "expense"),
        CategoryUI("Health", Icons.Default.LocalHospital, Color(0xFFE91E63), "expense"),

        // INCOME
        CategoryUI("Salary", Icons.Default.AttachMoney, Color(0xFF10B981), "income"),
        CategoryUI("Freelance", Icons.Default.Work, Color(0xFF00BCD4), "income"),
        CategoryUI("Investment", Icons.Default.ShowChart, Color(0xFF4CAF50), "income"),
        CategoryUI("Gift", Icons.Default.CardGiftcard, Color(0xFFFFC107), "income"),

        // DEFAULT
        CategoryUI("Other", Icons.Default.Category, Color.Gray, "expense")
    )

    val typeList = listOf<String>(
        "income" ,
        "expense"
    )
}