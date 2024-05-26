package dev.pankaj.cleanarchitecture.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "User"
)
data class UserEntity(
    @PrimaryKey
    val id : String,
    val name: String
)
