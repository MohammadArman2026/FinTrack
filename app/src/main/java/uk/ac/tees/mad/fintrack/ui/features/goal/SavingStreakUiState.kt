package uk.ac.tees.mad.fintrack.ui.features.goal

data class SavingStreakUiState(
    val streakData: StreakData = StreakData(),
    val isLoading: Boolean = true ,
)

data class StreakData(
    val currentStreak: Int = 0,
    val dailyLimit: Float = 0f,
    val todayExpense: Double = 0.0,
    val isTodayOnTrack: Boolean = true
)
