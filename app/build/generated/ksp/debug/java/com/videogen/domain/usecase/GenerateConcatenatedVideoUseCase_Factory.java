package com.videogen.domain.usecase;

import com.videogen.studio.domain.repository.GeneratedVideoRepository;
import com.videogen.studio.domain.repository.VideoGeneratorRepository;
import com.videogen.studio.domain.usecase.GenerateConcatenatedVideoUseCase;

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
public final class GenerateConcatenatedVideoUseCase_Factory implements Factory<GenerateConcatenatedVideoUseCase> {
  private final Provider<VideoGeneratorRepository> generatorRepositoryProvider;

  private final Provider<GeneratedVideoRepository> generatedVideoRepositoryProvider;

  public GenerateConcatenatedVideoUseCase_Factory(
      Provider<VideoGeneratorRepository> generatorRepositoryProvider,
      Provider<GeneratedVideoRepository> generatedVideoRepositoryProvider) {
    this.generatorRepositoryProvider = generatorRepositoryProvider;
    this.generatedVideoRepositoryProvider = generatedVideoRepositoryProvider;
  }

  @Override
  public GenerateConcatenatedVideoUseCase get() {
    return newInstance(generatorRepositoryProvider.get(), generatedVideoRepositoryProvider.get());
  }

  public static GenerateConcatenatedVideoUseCase_Factory create(
      Provider<VideoGeneratorRepository> generatorRepositoryProvider,
      Provider<GeneratedVideoRepository> generatedVideoRepositoryProvider) {
    return new GenerateConcatenatedVideoUseCase_Factory(generatorRepositoryProvider, generatedVideoRepositoryProvider);
  }

  public static GenerateConcatenatedVideoUseCase newInstance(
      VideoGeneratorRepository generatorRepository,
      GeneratedVideoRepository generatedVideoRepository) {
    return new GenerateConcatenatedVideoUseCase(generatorRepository, generatedVideoRepository);
  }
}
