package uk.ac.tees.mad.fintrack.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'expense'")
    fun getTotalExpense(): Flow<Double?>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'income'")
    fun getTotalIncome(): Flow<Double?>

    @Query("SELECT SUM(CASE WHEN type = 'income' THEN amount ELSE 0 END) - SUM(CASE WHEN type = 'expense' THEN amount ELSE 0 END) FROM transactions")
    fun getTotalBalance(): Flow<Double?>

    @Query("SELECT * FROM transactions ORDER BY date DESC LIMIT 5")
    fun getRecentTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE type = :type")
    fun getAllTransactionByType(type : String) : Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE category = :category")
    fun getAllTransactionByCategory(category : String) : Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>
}

/**
 * insertTransaction() is responsible for inserting an entity into the database.
 */