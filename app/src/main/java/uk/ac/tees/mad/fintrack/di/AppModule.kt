package uk.ac.tees.mad.fintrack.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.ac.tees.mad.fintrack.core.utils.PreferenceManager
import uk.ac.tees.mad.fintrack.data.local.AppDatabase
import uk.ac.tees.mad.fintrack.data.local.TransactionDao
import uk.ac.tees.mad.fintrack.data.repository.TransactionRepositoryImpl
import uk.ac.tees.mad.fintrack.domain.repository.TransactionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(appDatabase : AppDatabase): TransactionDao{
       return appDatabase.getTransactionDao()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(dao : TransactionDao): TransactionRepository{
        return TransactionRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun providePreferenceManager(@ApplicationContext context : Context): PreferenceManager{
        return PreferenceManager(context)
    }
}