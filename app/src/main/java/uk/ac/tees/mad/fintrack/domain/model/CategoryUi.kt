package uk.ac.tees.mad.fintrack.domain.model

import android.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryUI(
    val name: String,
    val icon: ImageVector,
    val color: androidx.compose.ui.graphics.Color,
    val type: String
)