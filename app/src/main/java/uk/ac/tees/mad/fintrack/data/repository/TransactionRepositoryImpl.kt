package uk.ac.tees.mad.fintrack.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.ac.tees.mad.fintrack.core.utils.toEntity
import uk.ac.tees.mad.fintrack.core.utils.toTransaction
import uk.ac.tees.mad.fintrack.data.local.TransactionDao
import uk.ac.tees.mad.fintrack.data.local.TransactionEntity
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