package com.videogenerator.domain.usecase;

import com.videogenerator.domain.repository.GeneratedVideoRepository;
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
public final class GetGenerationHistoryUseCase_Factory implements Factory<GetGenerationHistoryUseCase> {
  private final Provider<GeneratedVideoRepository> repositoryProvider;

  public GetGenerationHistoryUseCase_Factory(
      Provider<GeneratedVideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetGenerationHistoryUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetGenerationHistoryUseCase_Factory create(
      Provider<GeneratedVideoRepository> repositoryProvider) {
    return new GetGenerationHistoryUseCase_Factory(repositoryProvider);
  }

  public static GetGenerationHistoryUseCase newInstance(GeneratedVideoRepository repository) {
    return new GetGenerationHistoryUseCase(repository);
  }
}
