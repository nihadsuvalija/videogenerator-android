package com.videogenerator.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppPaths_Factory implements Factory<AppPaths> {
  private final Provider<Context> contextProvider;

  public AppPaths_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AppPaths get() {
    return newInstance(contextProvider.get());
  }

  public static AppPaths_Factory create(Provider<Context> contextProvider) {
    return new AppPaths_Factory(contextProvider);
  }

  public static AppPaths newInstance(Context context) {
    return new AppPaths(context);
  }
}
