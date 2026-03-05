package com.videogen.studio.di

import android.content.Context
import androidx.room.Room
import com.videogen.studio.data.local.VideoGeneratorDatabase
import com.videogen.studio.data.local.dao.GeneratedVideoDao
import com.videogen.studio.data.local.dao.ImageBatchDao
import com.videogen.studio.data.local.dao.VideoBatchDao
import com.videogen.studio.data.repository.GeneratedVideoRepositoryImpl
import com.videogen.studio.data.repository.ImageBatchRepositoryImpl
import com.videogen.studio.data.repository.VideoBatchRepositoryImpl
import com.videogen.studio.data.repository.VideoGeneratorRepositoryImpl
import com.videogen.studio.domain.repository.GeneratedVideoRepository
import com.videogen.studio.domain.repository.ImageBatchRepository
import com.videogen.studio.domain.repository.VideoBatchRepository
import com.videogen.studio.domain.repository.VideoGeneratorRepository
import com.videogen.studio.util.AppPaths
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VideoGeneratorDatabase =
        Room.databaseBuilder(
            context,
            VideoGeneratorDatabase::class.java,
            "video_generator.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideVideoBatchDao(db: VideoGeneratorDatabase): VideoBatchDao = db.videoBatchDao()

    @Provides
    fun provideImageBatchDao(db: VideoGeneratorDatabase): ImageBatchDao = db.imageBatchDao()

    @Provides
    fun provideGeneratedVideoDao(db: VideoGeneratorDatabase): GeneratedVideoDao = db.generatedVideoDao()

    @Provides
    @Singleton
    fun provideAppPaths(@ApplicationContext context: Context): AppPaths = AppPaths(context)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVideoBatchRepository(
        impl: VideoBatchRepositoryImpl
    ): VideoBatchRepository

    @Binds
    @Singleton
    abstract fun bindImageBatchRepository(
        impl: ImageBatchRepositoryImpl
    ): ImageBatchRepository

    @Binds
    @Singleton
    abstract fun bindGeneratedVideoRepository(
        impl: GeneratedVideoRepositoryImpl
    ): GeneratedVideoRepository

    @Binds
    @Singleton
    abstract fun bindVideoGeneratorRepository(
        impl: VideoGeneratorRepositoryImpl
    ): VideoGeneratorRepository
}
