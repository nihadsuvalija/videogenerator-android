package com.videogen.di;

import com.videogen.studio.data.local.VideoGeneratorDatabase;
import com.videogen.studio.data.local.dao.GeneratedVideoDao;
import com.videogen.studio.di.DatabaseModule;

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
public final class DatabaseModule_ProvideGeneratedVideoDaoFactory implements Factory<GeneratedVideoDao> {
  private final Provider<VideoGeneratorDatabase> dbProvider;

  public DatabaseModule_ProvideGeneratedVideoDaoFactory(
      Provider<VideoGeneratorDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public GeneratedVideoDao get() {
    return provideGeneratedVideoDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideGeneratedVideoDaoFactory create(
      Provider<VideoGeneratorDatabase> dbProvider) {
    return new DatabaseModule_ProvideGeneratedVideoDaoFactory(dbProvider);
  }

  public static GeneratedVideoDao provideGeneratedVideoDao(VideoGeneratorDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideGeneratedVideoDao(db));
  }
}
