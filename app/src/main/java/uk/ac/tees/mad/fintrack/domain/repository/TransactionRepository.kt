package uk.ac.tees.mad.fintrack.domain.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.fintrack.data.local.TransactionEntity
import uk.ac.tees.mad.fintrack.domain.model.Transaction

interface TransactionRepository {
    suspend fun insertTransaction(transaction: Transaction): Result<Unit>

    fun getTotalIncome(): Flow<Double?>

    fun getTotalExpense(): Flow<Double?>

    fun getTotalBalance(): Flow<Double?>

    fun getRecentTransaction(): Flow<List<Transaction>>

    fun getAllTransactions(): Flow<List<Transaction>>

}