package com.kudos.focusincoroutines.section3.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userName: String,
    val userEmail: String,
    val password: Int,
): java.io.Serializable
