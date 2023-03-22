package com.example.wwcrewmanagement.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wwcrewmanagement.data.local.database.entities.WorkerEntity

@Database(
    entities = [
        WorkerEntity::class
    ],
    version = 1
)
abstract class WorkerDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao

}