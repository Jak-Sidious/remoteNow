package com.example.readynow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.readynow.data.model.User
import com.example.readynow.data.model.UserDao

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract val dao: UserDao
}