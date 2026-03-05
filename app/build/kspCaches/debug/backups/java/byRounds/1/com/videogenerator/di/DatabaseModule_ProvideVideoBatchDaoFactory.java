package com.videogenerator.di;

import com.videogenerator.data.local.VideoGeneratorDatabase;
import com.videogenerator.data.local.dao.VideoBatchDao;
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
public final class DatabaseModule_ProvideVideoBatchDaoFactory implements Factory<VideoBatchDao> {
  private final Provider<VideoGeneratorDatabase> dbProvider;

  public DatabaseModule_ProvideVideoBatchDaoFactory(Provider<VideoGeneratorDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public VideoBatchDao get() {
    return provideVideoBatchDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideVideoBatchDaoFactory create(
      Provider<VideoGeneratorDatabase> dbProvider) {
    return new DatabaseModule_ProvideVideoBatchDaoFactory(dbProvider);
  }

  public static VideoBatchDao provideVideoBatchDao(VideoGeneratorDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideVideoBatchDao(db));
  }
}
