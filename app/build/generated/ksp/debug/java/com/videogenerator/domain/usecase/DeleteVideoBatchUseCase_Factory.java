package com.videogenerator.domain.usecase;

import com.videogenerator.domain.repository.VideoBatchRepository;
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
public final class DeleteVideoBatchUseCase_Factory implements Factory<DeleteVideoBatchUseCase> {
  private final Provider<VideoBatchRepository> repositoryProvider;

  public DeleteVideoBatchUseCase_Factory(Provider<VideoBatchRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteVideoBatchUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteVideoBatchUseCase_Factory create(
      Provider<VideoBatchRepository> repositoryProvider) {
    return new DeleteVideoBatchUseCase_Factory(repositoryProvider);
  }

  public static DeleteVideoBatchUseCase newInstance(VideoBatchRepository repository) {
    return new DeleteVideoBatchUseCase(repository);
  }
}
