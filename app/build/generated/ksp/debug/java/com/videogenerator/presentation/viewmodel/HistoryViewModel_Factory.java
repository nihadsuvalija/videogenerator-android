package com.videogenerator.presentation.viewmodel;

import com.videogenerator.domain.usecase.DeleteGeneratedVideoUseCase;
import com.videogenerator.domain.usecase.GetGenerationHistoryUseCase;
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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<GetGenerationHistoryUseCase> getGenerationHistoryUseCaseProvider;

  private final Provider<DeleteGeneratedVideoUseCase> deleteGeneratedVideoUseCaseProvider;

  public HistoryViewModel_Factory(
      Provider<GetGenerationHistoryUseCase> getGenerationHistoryUseCaseProvider,
      Provider<DeleteGeneratedVideoUseCase> deleteGeneratedVideoUseCaseProvider) {
    this.getGenerationHistoryUseCaseProvider = getGenerationHistoryUseCaseProvider;
    this.deleteGeneratedVideoUseCaseProvider = deleteGeneratedVideoUseCaseProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(getGenerationHistoryUseCaseProvider.get(), deleteGeneratedVideoUseCaseProvider.get());
  }

  public static HistoryViewModel_Factory create(
      Provider<GetGenerationHistoryUseCase> getGenerationHistoryUseCaseProvider,
      Provider<DeleteGeneratedVideoUseCase> deleteGeneratedVideoUseCaseProvider) {
    return new HistoryViewModel_Factory(getGenerationHistoryUseCaseProvider, deleteGeneratedVideoUseCaseProvider);
  }

  public static HistoryViewModel newInstance(
      GetGenerationHistoryUseCase getGenerationHistoryUseCase,
      DeleteGeneratedVideoUseCase deleteGeneratedVideoUseCase) {
    return new HistoryViewModel(getGenerationHistoryUseCase, deleteGeneratedVideoUseCase);
  }
}
