package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
/* loaded from: classes.dex */
public class l implements HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ViewModelStoreOwner {
    public final Fragment h;
    public final ViewModelStore i;
    public ViewModelProvider.Factory j;
    public LifecycleRegistry k = null;
    public SavedStateRegistryController l = null;

    public l(@NonNull Fragment fragment, @NonNull ViewModelStore viewModelStore) {
        this.h = fragment;
        this.i = viewModelStore;
    }

    public void a(@NonNull Lifecycle.Event event) {
        this.k.handleLifecycleEvent(event);
    }

    public void b() {
        if (this.k == null) {
            this.k = new LifecycleRegistry(this);
            this.l = SavedStateRegistryController.create(this);
        }
    }

    public boolean c() {
        return this.k != null;
    }

    public void d(@Nullable Bundle bundle) {
        this.l.performRestore(bundle);
    }

    public void e(@NonNull Bundle bundle) {
        this.l.performSave(bundle);
    }

    public void f(@NonNull Lifecycle.State state) {
        this.k.setCurrentState(state);
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    @NonNull
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        ViewModelProvider.Factory defaultViewModelProviderFactory = this.h.getDefaultViewModelProviderFactory();
        if (!defaultViewModelProviderFactory.equals(this.h.mDefaultFactory)) {
            this.j = defaultViewModelProviderFactory;
            return defaultViewModelProviderFactory;
        }
        if (this.j == null) {
            Application application = null;
            Context applicationContext = this.h.requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    break;
                } else if (applicationContext instanceof Application) {
                    application = applicationContext;
                    break;
                } else {
                    applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
                }
            }
            this.j = new SavedStateViewModelFactory(application, this, this.h.getArguments());
        }
        return this.j;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        b();
        return this.k;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    @NonNull
    public SavedStateRegistry getSavedStateRegistry() {
        b();
        return this.l.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    @NonNull
    public ViewModelStore getViewModelStore() {
        b();
        return this.i;
    }
}
