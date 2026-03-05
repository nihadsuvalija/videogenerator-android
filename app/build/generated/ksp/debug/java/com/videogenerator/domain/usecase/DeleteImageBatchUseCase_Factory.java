package com.videogenerator.domain.usecase;

import com.videogenerator.domain.repository.ImageBatchRepository;
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
public final class DeleteImageBatchUseCase_Factory implements Factory<DeleteImageBatchUseCase> {
  private final Provider<ImageBatchRepository> repositoryProvider;

  public DeleteImageBatchUseCase_Factory(Provider<ImageBatchRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteImageBatchUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteImageBatchUseCase_Factory create(
      Provider<ImageBatchRepository> repositoryProvider) {
    return new DeleteImageBatchUseCase_Factory(repositoryProvider);
  }

  public static DeleteImageBatchUseCase newInstance(ImageBatchRepository repository) {
    return new DeleteImageBatchUseCase(repository);
  }
}
