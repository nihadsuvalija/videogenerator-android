package com.videogen.data.repository;

import com.videogen.studio.data.local.dao.ImageBatchDao;
import com.videogen.studio.data.repository.ImageBatchRepositoryImpl;

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
public final class ImageBatchRepositoryImpl_Factory implements Factory<ImageBatchRepositoryImpl> {
  private final Provider<ImageBatchDao> daoProvider;

  public ImageBatchRepositoryImpl_Factory(Provider<ImageBatchDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public ImageBatchRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static ImageBatchRepositoryImpl_Factory create(Provider<ImageBatchDao> daoProvider) {
    return new ImageBatchRepositoryImpl_Factory(daoProvider);
  }

  public static ImageBatchRepositoryImpl newInstance(ImageBatchDao dao) {
    return new ImageBatchRepositoryImpl(dao);
  }
}
