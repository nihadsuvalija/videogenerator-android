package com.videogenerator.data.repository;

import android.content.Context;
import com.videogenerator.util.AppPaths;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class VideoGeneratorRepositoryImpl_Factory implements Factory<VideoGeneratorRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<AppPaths> appPathsProvider;

  public VideoGeneratorRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<AppPaths> appPathsProvider) {
    this.contextProvider = contextProvider;
    this.appPathsProvider = appPathsProvider;
  }

  @Override
  public VideoGeneratorRepositoryImpl get() {
    return newInstance(contextProvider.get(), appPathsProvider.get());
  }

  public static VideoGeneratorRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<AppPaths> appPathsProvider) {
    return new VideoGeneratorRepositoryImpl_Factory(contextProvider, appPathsProvider);
  }

  public static VideoGeneratorRepositoryImpl newInstance(Context context, AppPaths appPaths) {
    return new VideoGeneratorRepositoryImpl(context, appPaths);
  }
}
