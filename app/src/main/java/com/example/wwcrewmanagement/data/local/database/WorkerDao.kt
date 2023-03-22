package com.example.wwcrewmanagement.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wwcrewmanagement.data.local.database.entities.WorkerEntity

@Dao
interface WorkerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorker(workerEntity: WorkerEntity)

    @Query("SELECT * FROM WorkerEntity WHERE id = :workerId")
    suspend fun getWorker(workerId: Int): WorkerEntity
}