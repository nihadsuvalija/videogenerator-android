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
public final class GetAllVideoBatchesUseCase_Factory implements Factory<GetAllVideoBatchesUseCase> {
  private final Provider<VideoBatchRepository> repositoryProvider;

  public GetAllVideoBatchesUseCase_Factory(Provider<VideoBatchRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllVideoBatchesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllVideoBatchesUseCase_Factory create(
      Provider<VideoBatchRepository> repositoryProvider) {
    return new GetAllVideoBatchesUseCase_Factory(repositoryProvider);
  }

  public static GetAllVideoBatchesUseCase newInstance(VideoBatchRepository repository) {
    return new GetAllVideoBatchesUseCase(repository);
  }
}
