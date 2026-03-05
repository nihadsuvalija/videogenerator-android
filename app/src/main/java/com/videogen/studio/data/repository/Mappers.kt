package com.videogen.studio.data.repository

import com.videogen.studio.data.local.entity.GeneratedVideoEntity
import com.videogen.studio.data.local.entity.ImageBatchEntity
import com.videogen.studio.data.local.entity.VideoBatchEntity
import com.videogen.studio.domain.model.GeneratedVideo
import com.videogen.studio.domain.model.GenerationType
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.VideoBatchItem

fun VideoBatchEntity.toDomain() = VideoBatchItem(
    id = id,
    batchName = batchName,
    folderPath = folderPath,
    videoCount = videoCount,
    createdAt = createdAt
)

fun VideoBatchItem.toEntity() = VideoBatchEntity(
    id = id,
    batchName = batchName,
    folderPath = folderPath,
    videoCount = videoCount,
    createdAt = createdAt
)

fun ImageBatchEntity.toDomain() = ImageBatchItem(
    id = id,
    batchName = batchName,
    folderPath = folderPath,
    imageCount = imageCount,
    createdAt = createdAt
)

fun ImageBatchItem.toEntity() = ImageBatchEntity(
    id = id,
    batchName = batchName,
    folderPath = folderPath,
    imageCount = imageCount,
    createdAt = createdAt
)

fun GeneratedVideoEntity.toDomain() = GeneratedVideo(
    id = id,
    outputPath = outputPath,
    duration = duration,
    width = width,
    height = height,
    type = GenerationType.valueOf(type),
    batchName = batchName,
    createdAt = createdAt
)

fun GeneratedVideo.toEntity() = GeneratedVideoEntity(
    id = id,
    outputPath = outputPath,
    duration = duration,
    width = width,
    height = height,
    type = type.name,
    batchName = batchName,
    createdAt = createdAt
)
