package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public final class LiveDataObservable<T> implements Observable<T> {

    /* renamed from: a  reason: collision with root package name */
    public final MutableLiveData<Result<T>> f705a = new MutableLiveData<>();
    @GuardedBy("mObservers")
    public final Map<Observable.Observer<T>, d<T>> b = new HashMap();

    /* loaded from: classes.dex */
    public static final class Result<T> {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public T f706a;
        @Nullable
        public Throwable b;

        public Result(@Nullable T t, @Nullable Throwable th) {
            this.f706a = t;
            this.b = th;
        }

        public static <T> Result<T> a(@NonNull Throwable th) {
            return new Result<>(null, (Throwable) Preconditions.checkNotNull(th));
        }

        public static <T> Result<T> b(@Nullable T t) {
            return new Result<>(t, null);
        }

        public boolean completedSuccessfully() {
            return this.b == null;
        }

        @Nullable
        public Throwable getError() {
            return this.b;
        }

        @Nullable
        public T getValue() {
            if (completedSuccessfully()) {
                return this.f706a;
            }
            throw new IllegalStateException("Result contains an error. Does not contain a value.");
        }

        @NonNull
        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("[Result: <");
            if (completedSuccessfully()) {
                str = "Value: " + this.f706a;
            } else {
                str = "Error: " + this.b;
            }
            sb.append(str);
            sb.append(">]");
            return sb.toString();
        }
    }

    /* loaded from: classes.dex */
    public class a implements CallbackToFutureAdapter.Resolver<T> {

        /* renamed from: androidx.camera.core.impl.LiveDataObservable$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0115a implements Runnable {
            public final /* synthetic */ CallbackToFutureAdapter.Completer h;

            public RunnableC0115a(CallbackToFutureAdapter.Completer completer) {
                this.h = completer;
            }

            @Override // java.lang.Runnable
            public void run() {
                Result<T> value = LiveDataObservable.this.f705a.getValue();
                if (value == null) {
                    this.h.setException(new IllegalStateException("Observable has not yet been initialized with a value."));
                } else if (value.completedSuccessfully()) {
                    this.h.set(value.getValue());
                } else {
                    Preconditions.checkNotNull(value.getError());
                    this.h.setException(value.getError());
                }
            }
        }

        public a() {
        }

        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        @Nullable
        public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<T> completer) {
            CameraXExecutors.mainThreadExecutor().execute(new RunnableC0115a(completer));
            return LiveDataObservable.this + " [fetch@" + SystemClock.uptimeMillis() + "]";
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ d h;
        public final /* synthetic */ d i;

        public b(d dVar, d dVar2) {
            this.h = dVar;
            this.i = dVar2;
        }

        @Override // java.lang.Runnable
        public void run() {
            LiveDataObservable.this.f705a.removeObserver(this.h);
            LiveDataObservable.this.f705a.observeForever(this.i);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ d h;

        public c(d dVar) {
            this.h = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            LiveDataObservable.this.f705a.removeObserver(this.h);
        }
    }

    /* loaded from: classes.dex */
    public static final class d<T> implements Observer<Result<T>> {
        public final AtomicBoolean h = new AtomicBoolean(true);
        public final Observable.Observer<T> i;
        public final Executor j;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ Result h;

            public a(Result result) {
                this.h = result;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (d.this.h.get()) {
                    if (this.h.completedSuccessfully()) {
                        d.this.i.onNewData(this.h.getValue());
                        return;
                    }
                    Preconditions.checkNotNull(this.h.getError());
                    d.this.i.onError(this.h.getError());
                }
            }
        }

        public d(@NonNull Executor executor, @NonNull Observable.Observer<T> observer) {
            this.j = executor;
            this.i = observer;
        }

        public void a() {
            this.h.set(false);
        }

        @Override // androidx.lifecycle.Observer
        /* renamed from: b */
        public void onChanged(@NonNull Result<T> result) {
            this.j.execute(new a(result));
        }
    }

    @Override // androidx.camera.core.impl.Observable
    public void addObserver(@NonNull Executor executor, @NonNull Observable.Observer<T> observer) {
        synchronized (this.b) {
            d<T> dVar = this.b.get(observer);
            if (dVar != null) {
                dVar.a();
            }
            d<T> dVar2 = new d<>(executor, observer);
            this.b.put(observer, dVar2);
            CameraXExecutors.mainThreadExecutor().execute(new b(dVar, dVar2));
        }
    }

    @Override // androidx.camera.core.impl.Observable
    @NonNull
    public ListenableFuture<T> fetchData() {
        return CallbackToFutureAdapter.getFuture(new a());
    }

    @NonNull
    public LiveData<Result<T>> getLiveData() {
        return this.f705a;
    }

    public void postError(@NonNull Throwable th) {
        this.f705a.postValue(Result.a(th));
    }

    public void postValue(@Nullable T t) {
        this.f705a.postValue(Result.b(t));
    }

    @Override // androidx.camera.core.impl.Observable
    public void removeObserver(@NonNull Observable.Observer<T> observer) {
        synchronized (this.b) {
            d<T> remove = this.b.remove(observer);
            if (remove != null) {
                remove.a();
                CameraXExecutors.mainThreadExecutor().execute(new c(remove));
            }
        }
    }
}
