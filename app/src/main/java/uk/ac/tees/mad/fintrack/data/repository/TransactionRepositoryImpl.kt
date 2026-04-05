package uk.ac.tees.mad.fintrack.data.repository

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.ac.tees.mad.fintrack.core.utils.toEntity
import uk.ac.tees.mad.fintrack.core.utils.toTransaction
import uk.ac.tees.mad.fintrack.data.local.TransactionDao
import uk.ac.tees.mad.fintrack.data.local.TransactionEntity
import uk.ac.tees.mad.fintrack.domain.model.CategoryData
import uk.ac.tees.mad.fintrack.domain.model.Transaction
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor (private val transactionDao: TransactionDao): TransactionRepository {
    override suspend fun insertTransaction(transaction: Transaction): Result<Unit> {
        return try {
            val entity = transaction.toEntity()
            transactionDao.insertTransaction(entity)
            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun getTotalIncome(): Flow<Double?> {
        return transactionDao.getTotalIncome()
    }

    override fun getTotalExpense(): Flow<Double?>{
        return transactionDao.getTotalExpense()
    }

    override fun getTotalBalance(): Flow<Double?> {
        return transactionDao.getTotalBalance()
    }

    override fun getRecentTransaction(): Flow<List<Transaction>> {
        return transactionDao.getRecentTransactions().map {entities ->
            entities.map {
                it.toTransaction()
            }
        }
    }

    override fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions().map { entityList->
            entityList.map {
                it.toTransaction()
            }
        }
    }

    override suspend fun removeTransactionById(id: Int) {
        transactionDao.removeTransactionById(id)
    }

    override fun getCategorySplit(
        from: Long,
        to: Long
    ): Flow<List<CategoryData>> {
        return transactionDao.getExpensesBetween(from = from , to = to)
            .map {transactions->
                val totalExpense = transactions.sumOf { it.amount }
                val grouped = transactions
                    .groupBy { it.category }

                grouped.map { (categoryName, list) ->

                    val categoryTotal = list.sumOf { it.amount }
                    val percentage =
                        if (totalExpense == 0.0) 0f
                        else ((categoryTotal / totalExpense) * 100).toFloat()

                    CategoryData(
                        name = categoryName,
                        percentage = percentage,
                        amount = categoryTotal,
                        color = getCategoryColor(categoryName)
                    )
                }
            }
    }

    override suspend fun resetData(): Result<Unit> {
        return try {
            transactionDao.resetData()
            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }
}

fun getCategoryColor(category: String): Color {
    return when (category) {
        "Food & Dining" -> Color(0xFF4CAF50)
        "Groceries" -> Color(0xFF8BC34A)
        "Transport" -> Color(0xFF2196F3)
        "Shopping" -> Color(0xFF9C27B0)
        "Entertainment" -> Color(0xFFFF9800)
        "Bills" -> Color(0xFF795548)
        "Health" -> Color(0xFFE91E63)
        else -> Color.Gray
    }
}

/**
 * TransactionRepositoryImpl implement the interface TransactionRepository.
 * insertTransaction() is responsible for insertion of entity.
 *
 * getRecentTransaction() is for getting the 5 recent transaction.
 * getAllTransaction() is for getting all the transaction.
 *
 * getTotalIncome() is for getting the total income.
 * getTotalExpense() is for getting the total expense.
 *
 * getTotalBalance() is for getting the total balance.
 */