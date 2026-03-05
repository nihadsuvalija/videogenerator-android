package com.videogenerator.presentation.viewmodel;

import com.videogenerator.domain.usecase.AddVideoBatchUseCase;
import com.videogenerator.domain.usecase.DeleteVideoBatchUseCase;
import com.videogenerator.domain.usecase.GetAllVideoBatchesUseCase;
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
public final class VideoBatchViewModel_Factory implements Factory<VideoBatchViewModel> {
  private final Provider<GetAllVideoBatchesUseCase> getAllVideoBatchesUseCaseProvider;

  private final Provider<AddVideoBatchUseCase> addVideoBatchUseCaseProvider;

  private final Provider<DeleteVideoBatchUseCase> deleteVideoBatchUseCaseProvider;

  public VideoBatchViewModel_Factory(
      Provider<GetAllVideoBatchesUseCase> getAllVideoBatchesUseCaseProvider,
      Provider<AddVideoBatchUseCase> addVideoBatchUseCaseProvider,
      Provider<DeleteVideoBatchUseCase> deleteVideoBatchUseCaseProvider) {
    this.getAllVideoBatchesUseCaseProvider = getAllVideoBatchesUseCaseProvider;
    this.addVideoBatchUseCaseProvider = addVideoBatchUseCaseProvider;
    this.deleteVideoBatchUseCaseProvider = deleteVideoBatchUseCaseProvider;
  }

  @Override
  public VideoBatchViewModel get() {
    return newInstance(getAllVideoBatchesUseCaseProvider.get(), addVideoBatchUseCaseProvider.get(), deleteVideoBatchUseCaseProvider.get());
  }

  public static VideoBatchViewModel_Factory create(
      Provider<GetAllVideoBatchesUseCase> getAllVideoBatchesUseCaseProvider,
      Provider<AddVideoBatchUseCase> addVideoBatchUseCaseProvider,
      Provider<DeleteVideoBatchUseCase> deleteVideoBatchUseCaseProvider) {
    return new VideoBatchViewModel_Factory(getAllVideoBatchesUseCaseProvider, addVideoBatchUseCaseProvider, deleteVideoBatchUseCaseProvider);
  }

  public static VideoBatchViewModel newInstance(GetAllVideoBatchesUseCase getAllVideoBatchesUseCase,
      AddVideoBatchUseCase addVideoBatchUseCase, DeleteVideoBatchUseCase deleteVideoBatchUseCase) {
    return new VideoBatchViewModel(getAllVideoBatchesUseCase, addVideoBatchUseCase, deleteVideoBatchUseCase);
  }
}
