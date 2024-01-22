package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class ComputableLiveData<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f1349a;
    public final LiveData<T> b;
    public final AtomicBoolean c;
    public final AtomicBoolean d;
    @VisibleForTesting
    public final Runnable e;
    @VisibleForTesting
    public final Runnable f;

    /* loaded from: classes.dex */
    public class a extends LiveData<T> {
        public a() {
        }

        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            ComputableLiveData computableLiveData = ComputableLiveData.this;
            computableLiveData.f1349a.execute(computableLiveData.e);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        @WorkerThread
        public void run() {
            do {
                boolean z = false;
                if (ComputableLiveData.this.d.compareAndSet(false, true)) {
                    Object obj = null;
                    boolean z2 = false;
                    while (ComputableLiveData.this.c.compareAndSet(true, false)) {
                        try {
                            obj = ComputableLiveData.this.compute();
                            z2 = true;
                        } catch (Throwable th) {
                            ComputableLiveData.this.d.set(false);
                            throw th;
                        }
                    }
                    if (z2) {
                        ComputableLiveData.this.b.postValue(obj);
                    }
                    ComputableLiveData.this.d.set(false);
                    z = z2;
                }
                if (!z) {
                    return;
                }
            } while (ComputableLiveData.this.c.get());
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            boolean hasActiveObservers = ComputableLiveData.this.b.hasActiveObservers();
            if (ComputableLiveData.this.c.compareAndSet(false, true) && hasActiveObservers) {
                ComputableLiveData computableLiveData = ComputableLiveData.this;
                computableLiveData.f1349a.execute(computableLiveData.e);
            }
        }
    }

    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    @WorkerThread
    public abstract T compute();

    @NonNull
    public LiveData<T> getLiveData() {
        return this.b;
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.f);
    }

    public ComputableLiveData(@NonNull Executor executor) {
        this.c = new AtomicBoolean(true);
        this.d = new AtomicBoolean(false);
        this.e = new b();
        this.f = new c();
        this.f1349a = executor;
        this.b = new a();
    }
}
