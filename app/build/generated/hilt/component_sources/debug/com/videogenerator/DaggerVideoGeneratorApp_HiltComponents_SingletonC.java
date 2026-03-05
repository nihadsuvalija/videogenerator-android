package com.videogenerator;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.videogenerator.data.local.VideoGeneratorDatabase;
import com.videogenerator.data.local.dao.GeneratedVideoDao;
import com.videogenerator.data.local.dao.ImageBatchDao;
import com.videogenerator.data.local.dao.VideoBatchDao;
import com.videogenerator.data.repository.GeneratedVideoRepositoryImpl;
import com.videogenerator.data.repository.ImageBatchRepositoryImpl;
import com.videogenerator.data.repository.VideoBatchRepositoryImpl;
import com.videogenerator.data.repository.VideoGeneratorRepositoryImpl;
import com.videogenerator.di.DatabaseModule_ProvideAppPathsFactory;
import com.videogenerator.di.DatabaseModule_ProvideDatabaseFactory;
import com.videogenerator.di.DatabaseModule_ProvideGeneratedVideoDaoFactory;
import com.videogenerator.di.DatabaseModule_ProvideImageBatchDaoFactory;
import com.videogenerator.di.DatabaseModule_ProvideVideoBatchDaoFactory;
import com.videogenerator.domain.repository.GeneratedVideoRepository;
import com.videogenerator.domain.repository.ImageBatchRepository;
import com.videogenerator.domain.repository.VideoBatchRepository;
import com.videogenerator.domain.repository.VideoGeneratorRepository;
import com.videogenerator.domain.usecase.AddImageBatchUseCase;
import com.videogenerator.domain.usecase.AddVideoBatchUseCase;
import com.videogenerator.domain.usecase.DeleteGeneratedVideoUseCase;
import com.videogenerator.domain.usecase.DeleteImageBatchUseCase;
import com.videogenerator.domain.usecase.DeleteVideoBatchUseCase;
import com.videogenerator.domain.usecase.GenerateConcatenatedVideoUseCase;
import com.videogenerator.domain.usecase.GenerateImageLoopVideoUseCase;
import com.videogenerator.domain.usecase.GetAllImageBatchesUseCase;
import com.videogenerator.domain.usecase.GetAllVideoBatchesUseCase;
import com.videogenerator.domain.usecase.GetGenerationHistoryUseCase;
import com.videogenerator.presentation.ui.batch.BatchesFragment;
import com.videogenerator.presentation.ui.batch.ImageBatchListFragment;
import com.videogenerator.presentation.ui.batch.VideoBatchListFragment;
import com.videogenerator.presentation.ui.generate.GenerateFragment;
import com.videogenerator.presentation.ui.history.HistoryFragment;
import com.videogenerator.presentation.ui.main.MainActivity;
import com.videogenerator.presentation.viewmodel.GenerateViewModel;
import com.videogenerator.presentation.viewmodel.GenerateViewModel_HiltModules_KeyModule_ProvideFactory;
import com.videogenerator.presentation.viewmodel.HistoryViewModel;
import com.videogenerator.presentation.viewmodel.HistoryViewModel_HiltModules_KeyModule_ProvideFactory;
import com.videogenerator.presentation.viewmodel.ImageBatchViewModel;
import com.videogenerator.presentation.viewmodel.ImageBatchViewModel_HiltModules_KeyModule_ProvideFactory;
import com.videogenerator.presentation.viewmodel.VideoBatchViewModel;
import com.videogenerator.presentation.viewmodel.VideoBatchViewModel_HiltModules_KeyModule_ProvideFactory;
import com.videogenerator.util.AppPaths;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerVideoGeneratorApp_HiltComponents_SingletonC {
  private DaggerVideoGeneratorApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public VideoGeneratorApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements VideoGeneratorApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements VideoGeneratorApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements VideoGeneratorApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements VideoGeneratorApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements VideoGeneratorApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements VideoGeneratorApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements VideoGeneratorApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public VideoGeneratorApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends VideoGeneratorApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends VideoGeneratorApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectBatchesFragment(BatchesFragment batchesFragment) {
    }

    @Override
    public void injectImageBatchListFragment(ImageBatchListFragment imageBatchListFragment) {
    }

    @Override
    public void injectVideoBatchListFragment(VideoBatchListFragment videoBatchListFragment) {
    }

    @Override
    public void injectGenerateFragment(GenerateFragment generateFragment) {
    }

    @Override
    public void injectHistoryFragment(HistoryFragment historyFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends VideoGeneratorApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends VideoGeneratorApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(4).add(GenerateViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(HistoryViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(ImageBatchViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(VideoBatchViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends VideoGeneratorApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<GenerateViewModel> generateViewModelProvider;

    private Provider<HistoryViewModel> historyViewModelProvider;

    private Provider<ImageBatchViewModel> imageBatchViewModelProvider;

    private Provider<VideoBatchViewModel> videoBatchViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    private GenerateConcatenatedVideoUseCase generateConcatenatedVideoUseCase() {
      return new GenerateConcatenatedVideoUseCase(singletonCImpl.bindVideoGeneratorRepositoryProvider.get(), singletonCImpl.bindGeneratedVideoRepositoryProvider.get());
    }

    private GenerateImageLoopVideoUseCase generateImageLoopVideoUseCase() {
      return new GenerateImageLoopVideoUseCase(singletonCImpl.bindVideoGeneratorRepositoryProvider.get(), singletonCImpl.bindGeneratedVideoRepositoryProvider.get());
    }

    private GetGenerationHistoryUseCase getGenerationHistoryUseCase() {
      return new GetGenerationHistoryUseCase(singletonCImpl.bindGeneratedVideoRepositoryProvider.get());
    }

    private DeleteGeneratedVideoUseCase deleteGeneratedVideoUseCase() {
      return new DeleteGeneratedVideoUseCase(singletonCImpl.bindGeneratedVideoRepositoryProvider.get());
    }

    private GetAllImageBatchesUseCase getAllImageBatchesUseCase() {
      return new GetAllImageBatchesUseCase(singletonCImpl.bindImageBatchRepositoryProvider.get());
    }

    private AddImageBatchUseCase addImageBatchUseCase() {
      return new AddImageBatchUseCase(singletonCImpl.bindImageBatchRepositoryProvider.get());
    }

    private DeleteImageBatchUseCase deleteImageBatchUseCase() {
      return new DeleteImageBatchUseCase(singletonCImpl.bindImageBatchRepositoryProvider.get());
    }

    private GetAllVideoBatchesUseCase getAllVideoBatchesUseCase() {
      return new GetAllVideoBatchesUseCase(singletonCImpl.bindVideoBatchRepositoryProvider.get());
    }

    private AddVideoBatchUseCase addVideoBatchUseCase() {
      return new AddVideoBatchUseCase(singletonCImpl.bindVideoBatchRepositoryProvider.get());
    }

    private DeleteVideoBatchUseCase deleteVideoBatchUseCase() {
      return new DeleteVideoBatchUseCase(singletonCImpl.bindVideoBatchRepositoryProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.generateViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.historyViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.imageBatchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.videoBatchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
    }

    @Override
    public Map<String, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(4).put("com.videogenerator.presentation.viewmodel.GenerateViewModel", ((Provider) generateViewModelProvider)).put("com.videogenerator.presentation.viewmodel.HistoryViewModel", ((Provider) historyViewModelProvider)).put("com.videogenerator.presentation.viewmodel.ImageBatchViewModel", ((Provider) imageBatchViewModelProvider)).put("com.videogenerator.presentation.viewmodel.VideoBatchViewModel", ((Provider) videoBatchViewModelProvider)).build();
    }

    @Override
    public Map<String, Object> getHiltViewModelAssistedMap() {
      return Collections.<String, Object>emptyMap();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.videogenerator.presentation.viewmodel.GenerateViewModel 
          return (T) new GenerateViewModel(viewModelCImpl.generateConcatenatedVideoUseCase(), viewModelCImpl.generateImageLoopVideoUseCase(), singletonCImpl.bindVideoBatchRepositoryProvider.get(), singletonCImpl.bindImageBatchRepositoryProvider.get());

          case 1: // com.videogenerator.presentation.viewmodel.HistoryViewModel 
          return (T) new HistoryViewModel(viewModelCImpl.getGenerationHistoryUseCase(), viewModelCImpl.deleteGeneratedVideoUseCase());

          case 2: // com.videogenerator.presentation.viewmodel.ImageBatchViewModel 
          return (T) new ImageBatchViewModel(viewModelCImpl.getAllImageBatchesUseCase(), viewModelCImpl.addImageBatchUseCase(), viewModelCImpl.deleteImageBatchUseCase());

          case 3: // com.videogenerator.presentation.viewmodel.VideoBatchViewModel 
          return (T) new VideoBatchViewModel(viewModelCImpl.getAllVideoBatchesUseCase(), viewModelCImpl.addVideoBatchUseCase(), viewModelCImpl.deleteVideoBatchUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends VideoGeneratorApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends VideoGeneratorApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends VideoGeneratorApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppPaths> provideAppPathsProvider;

    private Provider<VideoGeneratorRepositoryImpl> videoGeneratorRepositoryImplProvider;

    private Provider<VideoGeneratorRepository> bindVideoGeneratorRepositoryProvider;

    private Provider<VideoGeneratorDatabase> provideDatabaseProvider;

    private Provider<GeneratedVideoRepositoryImpl> generatedVideoRepositoryImplProvider;

    private Provider<GeneratedVideoRepository> bindGeneratedVideoRepositoryProvider;

    private Provider<VideoBatchRepositoryImpl> videoBatchRepositoryImplProvider;

    private Provider<VideoBatchRepository> bindVideoBatchRepositoryProvider;

    private Provider<ImageBatchRepositoryImpl> imageBatchRepositoryImplProvider;

    private Provider<ImageBatchRepository> bindImageBatchRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private GeneratedVideoDao generatedVideoDao() {
      return DatabaseModule_ProvideGeneratedVideoDaoFactory.provideGeneratedVideoDao(provideDatabaseProvider.get());
    }

    private VideoBatchDao videoBatchDao() {
      return DatabaseModule_ProvideVideoBatchDaoFactory.provideVideoBatchDao(provideDatabaseProvider.get());
    }

    private ImageBatchDao imageBatchDao() {
      return DatabaseModule_ProvideImageBatchDaoFactory.provideImageBatchDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideAppPathsProvider = DoubleCheck.provider(new SwitchingProvider<AppPaths>(singletonCImpl, 1));
      this.videoGeneratorRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindVideoGeneratorRepositoryProvider = DoubleCheck.provider((Provider) videoGeneratorRepositoryImplProvider);
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<VideoGeneratorDatabase>(singletonCImpl, 3));
      this.generatedVideoRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 2);
      this.bindGeneratedVideoRepositoryProvider = DoubleCheck.provider((Provider) generatedVideoRepositoryImplProvider);
      this.videoBatchRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 4);
      this.bindVideoBatchRepositoryProvider = DoubleCheck.provider((Provider) videoBatchRepositoryImplProvider);
      this.imageBatchRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 5);
      this.bindImageBatchRepositoryProvider = DoubleCheck.provider((Provider) imageBatchRepositoryImplProvider);
    }

    @Override
    public void injectVideoGeneratorApp(VideoGeneratorApp videoGeneratorApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.videogenerator.data.repository.VideoGeneratorRepositoryImpl 
          return (T) new VideoGeneratorRepositoryImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideAppPathsProvider.get());

          case 1: // com.videogenerator.util.AppPaths 
          return (T) DatabaseModule_ProvideAppPathsFactory.provideAppPaths(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.videogenerator.data.repository.GeneratedVideoRepositoryImpl 
          return (T) new GeneratedVideoRepositoryImpl(singletonCImpl.generatedVideoDao());

          case 3: // com.videogenerator.data.local.VideoGeneratorDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.videogenerator.data.repository.VideoBatchRepositoryImpl 
          return (T) new VideoBatchRepositoryImpl(singletonCImpl.videoBatchDao());

          case 5: // com.videogenerator.data.repository.ImageBatchRepositoryImpl 
          return (T) new ImageBatchRepositoryImpl(singletonCImpl.imageBatchDao());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
