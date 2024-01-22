package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
/* loaded from: classes.dex */
public abstract class AbstractSavedStateViewModelFactory extends ViewModelProvider.OnRequeryFactory implements ViewModelProvider.Factory {

    /* renamed from: a  reason: collision with root package name */
    public SavedStateRegistry f1347a;
    public Lifecycle b;
    public Bundle c;

    public AbstractSavedStateViewModelFactory() {
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls, @NonNull CreationExtras creationExtras) {
        String str = (String) creationExtras.get(ViewModelProvider.NewInstanceFactory.VIEW_MODEL_KEY);
        if (str != null) {
            if (this.f1347a != null) {
                return (T) create(str, cls);
            }
            return (T) create(str, cls, SavedStateHandleSupport.createSavedStateHandle(creationExtras));
        }
        throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
    }

    @NonNull
    public abstract <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls, @NonNull SavedStateHandle savedStateHandle);

    @Override // androidx.lifecycle.ViewModelProvider.OnRequeryFactory
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onRequery(@NonNull ViewModel viewModel) {
        SavedStateRegistry savedStateRegistry = this.f1347a;
        if (savedStateRegistry != null) {
            LegacySavedStateHandleController.a(viewModel, savedStateRegistry, this.b);
        }
    }

    @SuppressLint({"LambdaLast"})
    public AbstractSavedStateViewModelFactory(@NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle) {
        this.f1347a = savedStateRegistryOwner.getSavedStateRegistry();
        this.b = savedStateRegistryOwner.getLifecycle();
        this.c = bundle;
    }

    @NonNull
    public final <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls) {
        SavedStateHandleController b = LegacySavedStateHandleController.b(this.f1347a, this.b, str, this.c);
        T t = (T) create(str, cls, b.b());
        t.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", b);
        return t;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            if (this.b != null) {
                return (T) create(canonicalName, cls);
            }
            throw new UnsupportedOperationException("AbstractSavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
