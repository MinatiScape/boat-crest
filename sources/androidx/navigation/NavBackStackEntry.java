package androidx.navigation;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import java.util.UUID;
/* loaded from: classes.dex */
public final class NavBackStackEntry implements LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {
    public final Context h;
    public final NavDestination i;
    public Bundle j;
    public final LifecycleRegistry k;
    public final SavedStateRegistryController l;
    @NonNull
    public final UUID m;
    public Lifecycle.State n;
    public Lifecycle.State o;
    public androidx.navigation.a p;
    public ViewModelProvider.Factory q;
    public SavedStateHandle r;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f1436a;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            f1436a = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1436a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1436a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1436a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1436a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1436a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1436a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b extends AbstractSavedStateViewModelFactory {
        public b(@NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle) {
            super(savedStateRegistryOwner, bundle);
        }

        @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
        @NonNull
        public <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls, @NonNull SavedStateHandle savedStateHandle) {
            return new c(savedStateHandle);
        }
    }

    /* loaded from: classes.dex */
    public static class c extends ViewModel {

        /* renamed from: a  reason: collision with root package name */
        public SavedStateHandle f1437a;

        public c(SavedStateHandle savedStateHandle) {
            this.f1437a = savedStateHandle;
        }

        public SavedStateHandle h() {
            return this.f1437a;
        }
    }

    public NavBackStackEntry(@NonNull Context context, @NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable LifecycleOwner lifecycleOwner, @Nullable androidx.navigation.a aVar) {
        this(context, navDestination, bundle, lifecycleOwner, aVar, UUID.randomUUID(), null);
    }

    @NonNull
    public static Lifecycle.State b(@NonNull Lifecycle.Event event) {
        switch (a.f1436a[event.ordinal()]) {
            case 1:
            case 2:
                return Lifecycle.State.CREATED;
            case 3:
            case 4:
                return Lifecycle.State.STARTED;
            case 5:
                return Lifecycle.State.RESUMED;
            case 6:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    @NonNull
    public Lifecycle.State a() {
        return this.o;
    }

    public void c(@NonNull Lifecycle.Event event) {
        this.n = b(event);
        g();
    }

    public void d(@Nullable Bundle bundle) {
        this.j = bundle;
    }

    public void e(@NonNull Bundle bundle) {
        this.l.performSave(bundle);
    }

    public void f(@NonNull Lifecycle.State state) {
        this.o = state;
        g();
    }

    public void g() {
        if (this.n.ordinal() < this.o.ordinal()) {
            this.k.setCurrentState(this.n);
        } else {
            this.k.setCurrentState(this.o);
        }
    }

    @Nullable
    public Bundle getArguments() {
        return this.j;
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    @NonNull
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        if (this.q == null) {
            this.q = new SavedStateViewModelFactory((Application) this.h.getApplicationContext(), this, this.j);
        }
        return this.q;
    }

    @NonNull
    public NavDestination getDestination() {
        return this.i;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.k;
    }

    @NonNull
    public SavedStateHandle getSavedStateHandle() {
        if (this.r == null) {
            this.r = ((c) new ViewModelProvider(this, new b(this, null)).get(c.class)).h();
        }
        return this.r;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    @NonNull
    public SavedStateRegistry getSavedStateRegistry() {
        return this.l.getSavedStateRegistry();
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    @NonNull
    public ViewModelStore getViewModelStore() {
        androidx.navigation.a aVar = this.p;
        if (aVar != null) {
            return aVar.c(this.m);
        }
        throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
    }

    public NavBackStackEntry(@NonNull Context context, @NonNull NavDestination navDestination, @Nullable Bundle bundle, @Nullable LifecycleOwner lifecycleOwner, @Nullable androidx.navigation.a aVar, @NonNull UUID uuid, @Nullable Bundle bundle2) {
        this.k = new LifecycleRegistry(this);
        SavedStateRegistryController create = SavedStateRegistryController.create(this);
        this.l = create;
        this.n = Lifecycle.State.CREATED;
        this.o = Lifecycle.State.RESUMED;
        this.h = context;
        this.m = uuid;
        this.i = navDestination;
        this.j = bundle;
        this.p = aVar;
        create.performRestore(bundle2);
        if (lifecycleOwner != null) {
            this.n = lifecycleOwner.getLifecycle().getCurrentState();
        }
    }
}
