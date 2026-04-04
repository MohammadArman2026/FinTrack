package uk.ac.tees.mad.fintrack.ui.features.insights.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.domain.model.CategoryData

@Composable
fun DonutChart(
    categories: List<CategoryData>,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .size(200.dp)
    ) {
        val total = categories.sumOf { it.percentage.toDouble() }.toFloat()
        var startAngle = -90f
        categories.forEach { category ->
            val sweepAngle = (category.percentage / total) * 360f

            drawArc(
                color = category.color,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = 40f, cap = StrokeCap.Butt)
            )
            startAngle += sweepAngle
        }
    }
}