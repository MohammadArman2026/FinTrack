package uk.ac.tees.mad.fintrack.ui.features.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.fintrack.ui.theme.Dimens

@Composable
fun StreakScreen(
    uiState: SavingStreakUiState,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            SavingStreakHero(uiState.streakData)
        }

        item {
            TodayStatusCard(uiState.streakData)
        }

        item {
            MotivationCard(uiState.streakData)
        }

        item {
            HistoryPlaceholder()
        }
    }
}



@Composable
fun SavingStreakHero(streak: StreakData) {

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "${streak.currentStreak}",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = "Day Streak",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun TodayStatusCard(streak: StreakData) {

    Card(
        shape = RoundedCornerShape(Dimens.cardRadius) ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ) ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.cardPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Text(
                text = "Today's Spending",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "₹${streak.todayExpense.toInt()} / ₹${streak.dailyLimit.toInt()}",
                style = MaterialTheme.typography.headlineSmall
            )

            val progress = if (streak.dailyLimit > 0) {
                (streak.todayExpense / streak.dailyLimit).toFloat().coerceIn(0f, 1f)
            } else {
                0f
            }

            LinearProgressIndicator(
                progress = progress ,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MotivationCard(streak: StreakData) {

    val message = when {
        streak.currentStreak == 0 -> "Start your streak today 💪"
        streak.currentStreak < 3 -> "Good start 👍"
        streak.currentStreak < 7 -> "Keep going 🔥"
        else -> "You're unstoppable 🚀"
    }

    Card(
        shape = RoundedCornerShape(Dimens.cardRadius) ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ) ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        )

    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HistoryPlaceholder() {
    Card(
        shape = RoundedCornerShape(Dimens.cardRadius) ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ) ,
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.cardElevation
        )
    ) {
        Text(
            text = "Last 7 Days Progress (Coming Soon)",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}