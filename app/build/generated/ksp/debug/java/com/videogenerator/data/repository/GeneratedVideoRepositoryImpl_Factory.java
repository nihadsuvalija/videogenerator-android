package com.videogenerator.data.repository;

import com.videogenerator.data.local.dao.GeneratedVideoDao;
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
public final class GeneratedVideoRepositoryImpl_Factory implements Factory<GeneratedVideoRepositoryImpl> {
  private final Provider<GeneratedVideoDao> daoProvider;

  public GeneratedVideoRepositoryImpl_Factory(Provider<GeneratedVideoDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public GeneratedVideoRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static GeneratedVideoRepositoryImpl_Factory create(
      Provider<GeneratedVideoDao> daoProvider) {
    return new GeneratedVideoRepositoryImpl_Factory(daoProvider);
  }

  public static GeneratedVideoRepositoryImpl newInstance(GeneratedVideoDao dao) {
    return new GeneratedVideoRepositoryImpl(dao);
  }
}
