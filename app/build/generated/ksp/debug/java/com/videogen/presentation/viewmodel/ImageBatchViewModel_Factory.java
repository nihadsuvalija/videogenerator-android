package com.videogen.presentation.viewmodel;

import com.videogen.studio.domain.usecase.AddImageBatchUseCase;
import com.videogen.studio.domain.usecase.DeleteImageBatchUseCase;
import com.videogen.studio.domain.usecase.GetAllImageBatchesUseCase;
import com.videogen.studio.presentation.viewmodel.ImageBatchViewModel;

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
public final class ImageBatchViewModel_Factory implements Factory<ImageBatchViewModel> {
  private final Provider<GetAllImageBatchesUseCase> getAllImageBatchesUseCaseProvider;

  private final Provider<AddImageBatchUseCase> addImageBatchUseCaseProvider;

  private final Provider<DeleteImageBatchUseCase> deleteImageBatchUseCaseProvider;

  public ImageBatchViewModel_Factory(
      Provider<GetAllImageBatchesUseCase> getAllImageBatchesUseCaseProvider,
      Provider<AddImageBatchUseCase> addImageBatchUseCaseProvider,
      Provider<DeleteImageBatchUseCase> deleteImageBatchUseCaseProvider) {
    this.getAllImageBatchesUseCaseProvider = getAllImageBatchesUseCaseProvider;
    this.addImageBatchUseCaseProvider = addImageBatchUseCaseProvider;
    this.deleteImageBatchUseCaseProvider = deleteImageBatchUseCaseProvider;
  }

  @Override
  public ImageBatchViewModel get() {
    return newInstance(getAllImageBatchesUseCaseProvider.get(), addImageBatchUseCaseProvider.get(), deleteImageBatchUseCaseProvider.get());
  }

  public static ImageBatchViewModel_Factory create(
      Provider<GetAllImageBatchesUseCase> getAllImageBatchesUseCaseProvider,
      Provider<AddImageBatchUseCase> addImageBatchUseCaseProvider,
      Provider<DeleteImageBatchUseCase> deleteImageBatchUseCaseProvider) {
    return new ImageBatchViewModel_Factory(getAllImageBatchesUseCaseProvider, addImageBatchUseCaseProvider, deleteImageBatchUseCaseProvider);
  }

  public static ImageBatchViewModel newInstance(GetAllImageBatchesUseCase getAllImageBatchesUseCase,
      AddImageBatchUseCase addImageBatchUseCase, DeleteImageBatchUseCase deleteImageBatchUseCase) {
    return new ImageBatchViewModel(getAllImageBatchesUseCase, addImageBatchUseCase, deleteImageBatchUseCase);
  }
}
