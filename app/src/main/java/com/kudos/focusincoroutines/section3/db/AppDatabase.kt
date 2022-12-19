package com.kudos.focusincoroutines.section3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kudos.focusincoroutines.section3.db.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

}