package dev.pankaj.cleanarchitecture.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import dev.pankaj.cleanarchitecture.data.db.entity.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1,
    autoMigrations = [
        AutoMigration(from = 1, to = 1)
    ]
)
class RoomDb {
}