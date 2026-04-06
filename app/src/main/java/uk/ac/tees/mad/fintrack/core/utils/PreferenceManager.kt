package uk.ac.tees.mad.fintrack.core.utils

import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class PreferenceManager @Inject constructor(context : Context) {
    private val sharedPreferences = context.getSharedPreferences("MY_PREFERENCES" ,Context.MODE_PRIVATE)

    companion object{
        const val KEY_IS_DARK_MODE = "dark_mode"
        const val KEY_DAILY_LIMIT = "daily_limit"
    }

    private val _isDarkMode : MutableStateFlow<Boolean> = MutableStateFlow(isDarkModeEnabled())
    val isDarkMode = _isDarkMode

    fun setDarkMode(value : Boolean){
        sharedPreferences.edit {
            putBoolean(
                KEY_IS_DARK_MODE ,value
            )
        }
        _isDarkMode.value = value
    }

    fun isDarkModeEnabled(): Boolean{
         return sharedPreferences.getBoolean(
            KEY_IS_DARK_MODE ,
            false
        )
    }

    fun setDailyLimit(value: Float){
        sharedPreferences.edit {
            putFloat(
                KEY_DAILY_LIMIT ,
                value
            )
        }
    }

    fun getDailyLimit(): Float {
        return sharedPreferences.getFloat(
            KEY_DAILY_LIMIT,
            100f
        )
    }
}