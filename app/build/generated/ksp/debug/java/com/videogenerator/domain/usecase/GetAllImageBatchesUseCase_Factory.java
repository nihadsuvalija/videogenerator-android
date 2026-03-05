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
public final class GetAllImageBatchesUseCase_Factory implements Factory<GetAllImageBatchesUseCase> {
  private final Provider<ImageBatchRepository> repositoryProvider;

  public GetAllImageBatchesUseCase_Factory(Provider<ImageBatchRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllImageBatchesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllImageBatchesUseCase_Factory create(
      Provider<ImageBatchRepository> repositoryProvider) {
    return new GetAllImageBatchesUseCase_Factory(repositoryProvider);
  }

  public static GetAllImageBatchesUseCase newInstance(ImageBatchRepository repository) {
    return new GetAllImageBatchesUseCase(repository);
  }
}
