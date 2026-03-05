package com.videogen.studio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.videogen.studio.data.local.dao.GeneratedVideoDao
import com.videogen.studio.data.local.dao.ImageBatchDao
import com.videogen.studio.data.local.dao.VideoBatchDao
import com.videogen.studio.data.local.entity.GeneratedVideoEntity
import com.videogen.studio.data.local.entity.ImageBatchEntity
import com.videogen.studio.data.local.entity.VideoBatchEntity

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
