package uk.ac.tees.mad.fintrack.domain.model

import androidx.compose.ui.graphics.Color

data class CategoryData(
    val name: String,
    val percentage: Float,
    val amount: Double,
    val color: Color
)