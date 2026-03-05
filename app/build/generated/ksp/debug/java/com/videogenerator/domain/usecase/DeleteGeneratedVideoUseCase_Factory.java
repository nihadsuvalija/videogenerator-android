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
public final class DeleteGeneratedVideoUseCase_Factory implements Factory<DeleteGeneratedVideoUseCase> {
  private final Provider<GeneratedVideoRepository> repositoryProvider;

  public DeleteGeneratedVideoUseCase_Factory(
      Provider<GeneratedVideoRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteGeneratedVideoUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteGeneratedVideoUseCase_Factory create(
      Provider<GeneratedVideoRepository> repositoryProvider) {
    return new DeleteGeneratedVideoUseCase_Factory(repositoryProvider);
  }

  public static DeleteGeneratedVideoUseCase newInstance(GeneratedVideoRepository repository) {
    return new DeleteGeneratedVideoUseCase(repository);
  }
}
