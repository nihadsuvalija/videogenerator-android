package com.videogen.studio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video_batches")
data class VideoBatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val batchName: String,
    val folderPath: String,
    val videoCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "image_batches")
data class ImageBatchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val batchName: String,
    val folderPath: String,
    val imageCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "generated_videos")
data class GeneratedVideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val outputPath: String,
    val duration: Double,
    val width: Int,
    val height: Int,
    val type: String,           // "VIDEO_CONCAT" or "IMAGE_LOOP"
    val batchName: String,
    val createdAt: Long = System.currentTimeMillis()
)
