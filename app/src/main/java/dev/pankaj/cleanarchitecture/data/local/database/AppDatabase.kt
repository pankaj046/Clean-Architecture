package dev.pankaj.cleanarchitecture.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.pankaj.cleanarchitecture.data.local.dao.UserDao
import dev.pankaj.cleanarchitecture.data.local.entity.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1,
    autoMigrations = [
        AutoMigration(from = 1, to = 1)
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}