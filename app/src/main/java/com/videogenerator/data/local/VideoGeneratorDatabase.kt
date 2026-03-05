package com.videogenerator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.videogenerator.data.local.dao.*
import com.videogenerator.data.local.entity.*

@Database(
    entities = [
        VideoBatchEntity::class,
        ImageBatchEntity::class,
        GeneratedVideoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class VideoGeneratorDatabase : RoomDatabase() {
    abstract fun videoBatchDao(): VideoBatchDao
    abstract fun imageBatchDao(): ImageBatchDao
    abstract fun generatedVideoDao(): GeneratedVideoDao
}
