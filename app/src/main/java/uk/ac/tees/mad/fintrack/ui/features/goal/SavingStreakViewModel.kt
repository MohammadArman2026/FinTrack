package uk.ac.tees.mad.fintrack.ui.features.goal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import uk.ac.tees.mad.fintrack.core.utils.PreferenceManager
import uk.ac.tees.mad.fintrack.domain.model.Transaction
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class SavingStreakViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository ,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<SavingStreakUiState> =
        transactionRepository.getAllTransactions()
            .map { transactions ->

                val streakData = calculateSavingStreak(
                    transactions = transactions,
                    dailyLimit = preferenceManager.getDailyLimit()
                )

                SavingStreakUiState(
                    streakData = streakData,
                    isLoading = false
                )
            }
            .flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SavingStreakUiState(
                    isLoading = true
                )
            )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateSavingStreak(
        transactions: List<Transaction>,
        dailyLimit: Float
    ): StreakData {

        val zoneId = ZoneId.systemDefault()

        val expenseByDate: Map<LocalDate, Double> = transactions
            .asSequence()
            .filter { it.type.equals("expense", ignoreCase = true) }
            .groupBy {
                Instant.ofEpochMilli(it.date)
                    .atZone(zoneId)
                    .toLocalDate()
            }
            .mapValues { entry ->
                entry.value.sumOf { it.amount ?: 0.0 }
            }

        fun isSuccessful(date: LocalDate): Boolean {
            val spent = expenseByDate[date]
            return spent != null && spent <= dailyLimit
        }

        val today = LocalDate.now(zoneId)

        var currentStreak = 0
        var day = today
        var daysChecked = 0
        val maxDays = 365

        while (isSuccessful(day) && daysChecked < maxDays) {
            currentStreak++
            day = day.minusDays(1)
            daysChecked++
        }

        val todayExpense = expenseByDate[today] ?: 0.0

        return StreakData(
            currentStreak = currentStreak,
            dailyLimit = dailyLimit,
            todayExpense = todayExpense,
            isTodayOnTrack = todayExpense <= dailyLimit
        )
    }
}