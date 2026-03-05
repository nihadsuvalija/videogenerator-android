package com.videogen.domain.usecase;

import com.videogen.studio.domain.repository.ImageBatchRepository;
import com.videogen.studio.domain.usecase.AddImageBatchUseCase;

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
public final class AddImageBatchUseCase_Factory implements Factory<AddImageBatchUseCase> {
  private final Provider<ImageBatchRepository> repositoryProvider;

  public AddImageBatchUseCase_Factory(Provider<ImageBatchRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddImageBatchUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddImageBatchUseCase_Factory create(
      Provider<ImageBatchRepository> repositoryProvider) {
    return new AddImageBatchUseCase_Factory(repositoryProvider);
  }

  public static AddImageBatchUseCase newInstance(ImageBatchRepository repository) {
    return new AddImageBatchUseCase(repository);
  }
}
