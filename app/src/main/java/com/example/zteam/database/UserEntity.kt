package com.example.zteam.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val username: String,
    val password: String,
    val fullName: String,
    val email: String
)
