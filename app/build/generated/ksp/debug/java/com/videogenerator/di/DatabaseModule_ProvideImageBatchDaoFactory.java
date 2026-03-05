package com.videogenerator.di;

import com.videogenerator.data.local.VideoGeneratorDatabase;
import com.videogenerator.data.local.dao.ImageBatchDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideImageBatchDaoFactory implements Factory<ImageBatchDao> {
  private final Provider<VideoGeneratorDatabase> dbProvider;

  public DatabaseModule_ProvideImageBatchDaoFactory(Provider<VideoGeneratorDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ImageBatchDao get() {
    return provideImageBatchDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideImageBatchDaoFactory create(
      Provider<VideoGeneratorDatabase> dbProvider) {
    return new DatabaseModule_ProvideImageBatchDaoFactory(dbProvider);
  }

  public static ImageBatchDao provideImageBatchDao(VideoGeneratorDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideImageBatchDao(db));
  }
}
