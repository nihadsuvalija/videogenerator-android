package com.videogen.presentation.viewmodel;

import com.videogen.studio.domain.repository.ImageBatchRepository;
import com.videogen.studio.domain.repository.VideoBatchRepository;
import com.videogen.studio.domain.usecase.GenerateConcatenatedVideoUseCase;
import com.videogen.studio.domain.usecase.GenerateImageLoopVideoUseCase;
import com.videogen.studio.presentation.viewmodel.GenerateViewModel;

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
public final class GenerateViewModel_Factory implements Factory<GenerateViewModel> {
  private final Provider<GenerateConcatenatedVideoUseCase> generateConcatenatedVideoUseCaseProvider;

  private final Provider<GenerateImageLoopVideoUseCase> generateImageLoopVideoUseCaseProvider;

  private final Provider<VideoBatchRepository> videoBatchRepositoryProvider;

  private final Provider<ImageBatchRepository> imageBatchRepositoryProvider;

  public GenerateViewModel_Factory(
      Provider<GenerateConcatenatedVideoUseCase> generateConcatenatedVideoUseCaseProvider,
      Provider<GenerateImageLoopVideoUseCase> generateImageLoopVideoUseCaseProvider,
      Provider<VideoBatchRepository> videoBatchRepositoryProvider,
      Provider<ImageBatchRepository> imageBatchRepositoryProvider) {
    this.generateConcatenatedVideoUseCaseProvider = generateConcatenatedVideoUseCaseProvider;
    this.generateImageLoopVideoUseCaseProvider = generateImageLoopVideoUseCaseProvider;
    this.videoBatchRepositoryProvider = videoBatchRepositoryProvider;
    this.imageBatchRepositoryProvider = imageBatchRepositoryProvider;
  }

  @Override
  public GenerateViewModel get() {
    return newInstance(generateConcatenatedVideoUseCaseProvider.get(), generateImageLoopVideoUseCaseProvider.get(), videoBatchRepositoryProvider.get(), imageBatchRepositoryProvider.get());
  }

  public static GenerateViewModel_Factory create(
      Provider<GenerateConcatenatedVideoUseCase> generateConcatenatedVideoUseCaseProvider,
      Provider<GenerateImageLoopVideoUseCase> generateImageLoopVideoUseCaseProvider,
      Provider<VideoBatchRepository> videoBatchRepositoryProvider,
      Provider<ImageBatchRepository> imageBatchRepositoryProvider) {
    return new GenerateViewModel_Factory(generateConcatenatedVideoUseCaseProvider, generateImageLoopVideoUseCaseProvider, videoBatchRepositoryProvider, imageBatchRepositoryProvider);
  }

  public static GenerateViewModel newInstance(
      GenerateConcatenatedVideoUseCase generateConcatenatedVideoUseCase,
      GenerateImageLoopVideoUseCase generateImageLoopVideoUseCase,
      VideoBatchRepository videoBatchRepository, ImageBatchRepository imageBatchRepository) {
    return new GenerateViewModel(generateConcatenatedVideoUseCase, generateImageLoopVideoUseCase, videoBatchRepository, imageBatchRepository);
  }
}
