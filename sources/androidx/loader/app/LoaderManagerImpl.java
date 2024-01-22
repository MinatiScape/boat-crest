package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
/* loaded from: classes.dex */
public class LoaderManagerImpl extends LoaderManager {
    public static boolean c = false;
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final LifecycleOwner f1384a;
    @NonNull
    public final b b;

    /* loaded from: classes.dex */
    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
        public final int l;
        @Nullable
        public final Bundle m;
        @NonNull
        public final Loader<D> n;
        public LifecycleOwner o;
        public a<D> p;
        public Loader<D> q;

        public LoaderInfo(int i, @Nullable Bundle bundle, @NonNull Loader<D> loader, @Nullable Loader<D> loader2) {
            this.l = i;
            this.m = bundle;
            this.n = loader;
            this.q = loader2;
            loader.registerListener(i, this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.print(str);
            printWriter.print("mId=");
            printWriter.print(this.l);
            printWriter.print(" mArgs=");
            printWriter.println(this.m);
            printWriter.print(str);
            printWriter.print("mLoader=");
            printWriter.println(this.n);
            Loader<D> loader = this.n;
            loader.dump(str + "  ", fileDescriptor, printWriter, strArr);
            if (this.p != null) {
                printWriter.print(str);
                printWriter.print("mCallbacks=");
                printWriter.println(this.p);
                a<D> aVar = this.p;
                aVar.a(str + "  ", printWriter);
            }
            printWriter.print(str);
            printWriter.print("mData=");
            printWriter.println(g().dataToString(getValue()));
            printWriter.print(str);
            printWriter.print("mStarted=");
            printWriter.println(hasActiveObservers());
        }

        @MainThread
        public Loader<D> f(boolean z) {
            if (LoaderManagerImpl.c) {
                Log.v("LoaderManager", "  Destroying: " + this);
            }
            this.n.cancelLoad();
            this.n.abandon();
            a<D> aVar = this.p;
            if (aVar != null) {
                removeObserver(aVar);
                if (z) {
                    aVar.c();
                }
            }
            this.n.unregisterListener(this);
            if ((aVar != null && !aVar.b()) || z) {
                this.n.reset();
                return this.q;
            }
            return this.n;
        }

        @NonNull
        public Loader<D> g() {
            return this.n;
        }

        public boolean h() {
            a<D> aVar;
            return (!hasActiveObservers() || (aVar = this.p) == null || aVar.b()) ? false : true;
        }

        public void i() {
            LifecycleOwner lifecycleOwner = this.o;
            a<D> aVar = this.p;
            if (lifecycleOwner == null || aVar == null) {
                return;
            }
            super.removeObserver(aVar);
            observe(lifecycleOwner, aVar);
        }

        @NonNull
        @MainThread
        public Loader<D> j(@NonNull LifecycleOwner lifecycleOwner, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            a<D> aVar = new a<>(this.n, loaderCallbacks);
            observe(lifecycleOwner, aVar);
            a<D> aVar2 = this.p;
            if (aVar2 != null) {
                removeObserver(aVar2);
            }
            this.o = lifecycleOwner;
            this.p = aVar;
            return this.n;
        }

        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            if (LoaderManagerImpl.c) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.n.startLoading();
        }

        @Override // androidx.lifecycle.LiveData
        public void onInactive() {
            if (LoaderManagerImpl.c) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.n.stopLoading();
        }

        @Override // androidx.loader.content.Loader.OnLoadCompleteListener
        public void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d) {
            if (LoaderManagerImpl.c) {
                Log.v("LoaderManager", "onLoadComplete: " + this);
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(d);
                return;
            }
            if (LoaderManagerImpl.c) {
                Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(d);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void removeObserver(@NonNull Observer<? super D> observer) {
            super.removeObserver(observer);
            this.o = null;
            this.p = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(D d) {
            super.setValue(d);
            Loader<D> loader = this.q;
            if (loader != null) {
                loader.reset();
                this.q = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.l);
            sb.append(" : ");
            DebugUtils.buildShortClassTag(this.n, sb);
            sb.append("}}");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class a<D> implements Observer<D> {
        @NonNull
        public final Loader<D> h;
        @NonNull
        public final LoaderManager.LoaderCallbacks<D> i;
        public boolean j = false;

        public a(@NonNull Loader<D> loader, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
            this.h = loader;
            this.i = loaderCallbacks;
        }

        public void a(String str, PrintWriter printWriter) {
            printWriter.print(str);
            printWriter.print("mDeliveredData=");
            printWriter.println(this.j);
        }

        public boolean b() {
            return this.j;
        }

        @MainThread
        public void c() {
            if (this.j) {
                if (LoaderManagerImpl.c) {
                    Log.v("LoaderManager", "  Resetting: " + this.h);
                }
                this.i.onLoaderReset(this.h);
            }
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(@Nullable D d) {
            if (LoaderManagerImpl.c) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.h + ": " + this.h.dataToString(d));
            }
            this.i.onLoadFinished(this.h, d);
            this.j = true;
        }

        public String toString() {
            return this.i.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class b extends ViewModel {
        public static final ViewModelProvider.Factory c = new a();

        /* renamed from: a  reason: collision with root package name */
        public SparseArrayCompat<LoaderInfo> f1385a = new SparseArrayCompat<>();
        public boolean b = false;

        /* loaded from: classes.dex */
        public static class a implements ViewModelProvider.Factory {
            @Override // androidx.lifecycle.ViewModelProvider.Factory
            @NonNull
            public <T extends ViewModel> T create(@NonNull Class<T> cls) {
                return new b();
            }
        }

        @NonNull
        public static b i(ViewModelStore viewModelStore) {
            return (b) new ViewModelProvider(viewModelStore, c).get(b.class);
        }

        public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            if (this.f1385a.size() > 0) {
                printWriter.print(str);
                printWriter.println("Loaders:");
                String str2 = str + "    ";
                for (int i = 0; i < this.f1385a.size(); i++) {
                    LoaderInfo valueAt = this.f1385a.valueAt(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(this.f1385a.keyAt(i));
                    printWriter.print(": ");
                    printWriter.println(valueAt.toString());
                    valueAt.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }

        public void h() {
            this.b = false;
        }

        public <D> LoaderInfo<D> j(int i) {
            return this.f1385a.get(i);
        }

        public boolean k() {
            int size = this.f1385a.size();
            for (int i = 0; i < size; i++) {
                if (this.f1385a.valueAt(i).h()) {
                    return true;
                }
            }
            return false;
        }

        public boolean l() {
            return this.b;
        }

        public void m() {
            int size = this.f1385a.size();
            for (int i = 0; i < size; i++) {
                this.f1385a.valueAt(i).i();
            }
        }

        public void n(int i, @NonNull LoaderInfo loaderInfo) {
            this.f1385a.put(i, loaderInfo);
        }

        public void o(int i) {
            this.f1385a.remove(i);
        }

        @Override // androidx.lifecycle.ViewModel
        public void onCleared() {
            super.onCleared();
            int size = this.f1385a.size();
            for (int i = 0; i < size; i++) {
                this.f1385a.valueAt(i).f(true);
            }
            this.f1385a.clear();
        }

        public void p() {
            this.b = true;
        }
    }

    public LoaderManagerImpl(@NonNull LifecycleOwner lifecycleOwner, @NonNull ViewModelStore viewModelStore) {
        this.f1384a = lifecycleOwner;
        this.b = b.i(viewModelStore);
    }

    @NonNull
    @MainThread
    public final <D> Loader<D> a(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks, @Nullable Loader<D> loader) {
        try {
            this.b.p();
            Loader<D> onCreateLoader = loaderCallbacks.onCreateLoader(i, bundle);
            if (onCreateLoader != null) {
                if (onCreateLoader.getClass().isMemberClass() && !Modifier.isStatic(onCreateLoader.getClass().getModifiers())) {
                    throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + onCreateLoader);
                }
                LoaderInfo loaderInfo = new LoaderInfo(i, bundle, onCreateLoader, loader);
                if (c) {
                    Log.v("LoaderManager", "  Created new loader " + loaderInfo);
                }
                this.b.n(i, loaderInfo);
                this.b.h();
                return loaderInfo.j(this.f1384a, loaderCallbacks);
            }
            throw new IllegalArgumentException("Object returned from onCreateLoader must not be null");
        } catch (Throwable th) {
            this.b.h();
            throw th;
        }
    }

    @Override // androidx.loader.app.LoaderManager
    @MainThread
    public void destroyLoader(int i) {
        if (!this.b.l()) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                if (c) {
                    Log.v("LoaderManager", "destroyLoader in " + this + " of " + i);
                }
                LoaderInfo j = this.b.j(i);
                if (j != null) {
                    j.f(true);
                    this.b.o(i);
                    return;
                }
                return;
            }
            throw new IllegalStateException("destroyLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.b.a(str, fileDescriptor, printWriter, strArr);
    }

    @Override // androidx.loader.app.LoaderManager
    @Nullable
    public <D> Loader<D> getLoader(int i) {
        if (!this.b.l()) {
            LoaderInfo<D> j = this.b.j(i);
            if (j != null) {
                return j.g();
            }
            return null;
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    @Override // androidx.loader.app.LoaderManager
    public boolean hasRunningLoaders() {
        return this.b.k();
    }

    @Override // androidx.loader.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> initLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (!this.b.l()) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                LoaderInfo<D> j = this.b.j(i);
                if (c) {
                    Log.v("LoaderManager", "initLoader in " + this + ": args=" + bundle);
                }
                if (j == null) {
                    return a(i, bundle, loaderCallbacks, null);
                }
                if (c) {
                    Log.v("LoaderManager", "  Re-using existing loader " + j);
                }
                return j.j(this.f1384a, loaderCallbacks);
            }
            throw new IllegalStateException("initLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    @Override // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.b.m();
    }

    @Override // androidx.loader.app.LoaderManager
    @NonNull
    @MainThread
    public <D> Loader<D> restartLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        if (!this.b.l()) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                if (c) {
                    Log.v("LoaderManager", "restartLoader in " + this + ": args=" + bundle);
                }
                LoaderInfo<D> j = this.b.j(i);
                return a(i, bundle, loaderCallbacks, j != null ? j.f(false) : null);
            }
            throw new IllegalStateException("restartLoader must be called on the main thread");
        }
        throw new IllegalStateException("Called while creating a loader");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        DebugUtils.buildShortClassTag(this.f1384a, sb);
        sb.append("}}");
        return sb.toString();
    }
}
