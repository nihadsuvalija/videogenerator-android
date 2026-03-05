package com.videogenerator.data.local.dao

import androidx.room.*
import com.videogenerator.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoBatchDao {
    @Query("SELECT * FROM video_batches ORDER BY createdAt DESC")
    fun getAll(): Flow<List<VideoBatchEntity>>

    @Query("SELECT * FROM video_batches WHERE id = :id")
    suspend fun getById(id: Long): VideoBatchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: VideoBatchEntity): Long

    @Update
    suspend fun update(entity: VideoBatchEntity)

    @Query("DELETE FROM video_batches WHERE id = :id")
    suspend fun deleteById(id: Long)
}

@Dao
interface ImageBatchDao {
    @Query("SELECT * FROM image_batches ORDER BY createdAt DESC")
    fun getAll(): Flow<List<ImageBatchEntity>>

    @Query("SELECT * FROM image_batches WHERE id = :id")
    suspend fun getById(id: Long): ImageBatchEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ImageBatchEntity): Long

    @Update
    suspend fun update(entity: ImageBatchEntity)

    @Query("DELETE FROM image_batches WHERE id = :id")
    suspend fun deleteById(id: Long)
}

@Dao
interface GeneratedVideoDao {
    @Query("SELECT * FROM generated_videos ORDER BY createdAt DESC")
    fun getAll(): Flow<List<GeneratedVideoEntity>>

    @Query("SELECT * FROM generated_videos WHERE id = :id")
    suspend fun getById(id: Long): GeneratedVideoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: GeneratedVideoEntity): Long

    @Query("DELETE FROM generated_videos WHERE id = :id")
    suspend fun deleteById(id: Long)
}
