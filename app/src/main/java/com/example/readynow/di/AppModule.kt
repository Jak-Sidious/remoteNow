package com.example.readynow.di

import android.app.Application
import androidx.room.Room
import com.example.readynow.data.AppDatabase
import com.example.readynow.data.model.UserDao
import com.example.readynow.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "my_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMyDao(db: AppDatabase): UserDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideRepository(dao: UserDao): UserRepository {
        return UserRepository(dao)
    }
}
