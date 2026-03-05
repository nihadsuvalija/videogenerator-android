package com.videogen.studio.data.repository

import com.videogen.studio.data.local.dao.GeneratedVideoDao
import com.videogen.studio.data.local.dao.ImageBatchDao
import com.videogen.studio.data.local.dao.VideoBatchDao
import com.videogen.studio.domain.model.GeneratedVideo
import com.videogen.studio.domain.model.ImageBatchItem
import com.videogen.studio.domain.model.VideoBatchItem
import com.videogen.studio.domain.repository.GeneratedVideoRepository
import com.videogen.studio.domain.repository.ImageBatchRepository
import com.videogen.studio.domain.repository.VideoBatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoBatchRepositoryImpl @Inject constructor(
    private val dao: VideoBatchDao
) : VideoBatchRepository {

    override fun getAllBatches(): Flow<List<VideoBatchItem>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun getBatchById(id: Long): VideoBatchItem? =
        dao.getById(id)?.toDomain()

    override suspend fun insertBatch(batch: VideoBatchItem): Long =
        dao.insert(batch.toEntity())

    override suspend fun deleteBatch(id: Long) = dao.deleteById(id)

    override suspend fun updateBatch(batch: VideoBatchItem) = dao.update(batch.toEntity())
}

class ImageBatchRepositoryImpl @Inject constructor(
    private val dao: ImageBatchDao
) : ImageBatchRepository {

    override fun getAllBatches(): Flow<List<ImageBatchItem>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun getBatchById(id: Long): ImageBatchItem? =
        dao.getById(id)?.toDomain()

    override suspend fun insertBatch(batch: ImageBatchItem): Long =
        dao.insert(batch.toEntity())

    override suspend fun deleteBatch(id: Long) = dao.deleteById(id)

    override suspend fun updateBatch(batch: ImageBatchItem) = dao.update(batch.toEntity())
}

class GeneratedVideoRepositoryImpl @Inject constructor(
    private val dao: GeneratedVideoDao
) : GeneratedVideoRepository {

    override fun getAllVideos(): Flow<List<GeneratedVideo>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun getVideoById(id: Long): GeneratedVideo? =
        dao.getById(id)?.toDomain()

    override suspend fun insertVideo(video: GeneratedVideo): Long =
        dao.insert(video.toEntity())

    override suspend fun deleteVideo(id: Long) = dao.deleteById(id)
}
