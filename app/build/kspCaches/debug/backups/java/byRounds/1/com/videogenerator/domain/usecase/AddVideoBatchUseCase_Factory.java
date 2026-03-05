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
public final class AddVideoBatchUseCase_Factory implements Factory<AddVideoBatchUseCase> {
  private final Provider<VideoBatchRepository> repositoryProvider;

  public AddVideoBatchUseCase_Factory(Provider<VideoBatchRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddVideoBatchUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static AddVideoBatchUseCase_Factory create(
      Provider<VideoBatchRepository> repositoryProvider) {
    return new AddVideoBatchUseCase_Factory(repositoryProvider);
  }

  public static AddVideoBatchUseCase newInstance(VideoBatchRepository repository) {
    return new AddVideoBatchUseCase(repository);
  }
}
