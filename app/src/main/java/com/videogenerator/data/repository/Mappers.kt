package com.videogenerator.data.repository

import com.videogenerator.data.local.entity.*
import com.videogenerator.domain.model.*

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
