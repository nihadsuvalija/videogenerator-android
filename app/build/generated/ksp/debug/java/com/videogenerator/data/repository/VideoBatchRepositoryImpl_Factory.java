package com.videogenerator.data.repository;

import com.videogenerator.data.local.dao.VideoBatchDao;
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
public final class VideoBatchRepositoryImpl_Factory implements Factory<VideoBatchRepositoryImpl> {
  private final Provider<VideoBatchDao> daoProvider;

  public VideoBatchRepositoryImpl_Factory(Provider<VideoBatchDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public VideoBatchRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static VideoBatchRepositoryImpl_Factory create(Provider<VideoBatchDao> daoProvider) {
    return new VideoBatchRepositoryImpl_Factory(daoProvider);
  }

  public static VideoBatchRepositoryImpl newInstance(VideoBatchDao dao) {
    return new VideoBatchRepositoryImpl(dao);
  }
}
