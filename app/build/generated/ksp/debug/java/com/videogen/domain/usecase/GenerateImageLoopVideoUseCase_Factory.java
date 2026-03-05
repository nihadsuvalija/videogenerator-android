package com.videogen.domain.usecase;

import com.videogen.studio.domain.repository.GeneratedVideoRepository;
import com.videogen.studio.domain.repository.VideoGeneratorRepository;
import com.videogen.studio.domain.usecase.GenerateImageLoopVideoUseCase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class GenerateImageLoopVideoUseCase_Factory implements Factory<GenerateImageLoopVideoUseCase> {
  private final Provider<VideoGeneratorRepository> generatorRepositoryProvider;

  private final Provider<GeneratedVideoRepository> generatedVideoRepositoryProvider;

  public GenerateImageLoopVideoUseCase_Factory(
      Provider<VideoGeneratorRepository> generatorRepositoryProvider,
      Provider<GeneratedVideoRepository> generatedVideoRepositoryProvider) {
    this.generatorRepositoryProvider = generatorRepositoryProvider;
    this.generatedVideoRepositoryProvider = generatedVideoRepositoryProvider;
  }

  @Override
  public GenerateImageLoopVideoUseCase get() {
    return newInstance(generatorRepositoryProvider.get(), generatedVideoRepositoryProvider.get());
  }

  public static GenerateImageLoopVideoUseCase_Factory create(
      Provider<VideoGeneratorRepository> generatorRepositoryProvider,
      Provider<GeneratedVideoRepository> generatedVideoRepositoryProvider) {
    return new GenerateImageLoopVideoUseCase_Factory(generatorRepositoryProvider, generatedVideoRepositoryProvider);
  }

  public static GenerateImageLoopVideoUseCase newInstance(
      VideoGeneratorRepository generatorRepository,
      GeneratedVideoRepository generatedVideoRepository) {
    return new GenerateImageLoopVideoUseCase(generatorRepository, generatedVideoRepository);
  }
}
