package com.kudos.focusincoroutines.section3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kudos.focusincoroutines.section3.db.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE userEmail = :userEmail")
    suspend fun getUser(userEmail: String): User

    @Delete
    suspend fun deleteUser(user: User)
}
