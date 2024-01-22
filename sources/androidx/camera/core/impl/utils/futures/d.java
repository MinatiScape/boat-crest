package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class d<V> implements ListenableFuture<List<V>> {
    @Nullable
    public List<? extends ListenableFuture<? extends V>> h;
    @Nullable
    public List<V> i;
    public final boolean j;
    @NonNull
    public final AtomicInteger k;
    @NonNull
    public final ListenableFuture<List<V>> l = CallbackToFutureAdapter.getFuture(new a());
    public CallbackToFutureAdapter.Completer<List<V>> m;

    /* loaded from: classes.dex */
    public class a implements CallbackToFutureAdapter.Resolver<List<V>> {
        public a() {
        }

        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<List<V>> completer) {
            Preconditions.checkState(d.this.m == null, "The result can only set once!");
            d.this.m = completer;
            return "ListFuture[" + this + "]";
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d dVar = d.this;
            dVar.i = null;
            dVar.h = null;
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ int h;
        public final /* synthetic */ ListenableFuture i;

        public c(int i, ListenableFuture listenableFuture) {
            this.h = i;
            this.i = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.e(this.h, this.i);
        }
    }

    public d(@NonNull List<? extends ListenableFuture<? extends V>> list, boolean z, @NonNull Executor executor) {
        this.h = (List) Preconditions.checkNotNull(list);
        this.i = new ArrayList(list.size());
        this.j = z;
        this.k = new AtomicInteger(list.size());
        d(executor);
    }

    public final void a() throws InterruptedException {
        List<? extends ListenableFuture<? extends V>> list = this.h;
        if (list == null || isDone()) {
            return;
        }
        for (ListenableFuture<? extends V> listenableFuture : list) {
            while (!listenableFuture.isDone()) {
                try {
                    listenableFuture.get();
                } catch (Error e) {
                    throw e;
                } catch (InterruptedException e2) {
                    throw e2;
                } catch (Throwable unused) {
                    if (this.j) {
                        return;
                    }
                }
            }
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
        this.l.addListener(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    @Nullable
    /* renamed from: b */
    public List<V> get() throws InterruptedException, ExecutionException {
        a();
        return this.l.get();
    }

    @Override // java.util.concurrent.Future
    /* renamed from: c */
    public List<V> get(long j, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.l.get(j, timeUnit);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        List<? extends ListenableFuture<? extends V>> list = this.h;
        if (list != null) {
            for (ListenableFuture<? extends V> listenableFuture : list) {
                listenableFuture.cancel(z);
            }
        }
        return this.l.cancel(z);
    }

    public final void d(@NonNull Executor executor) {
        addListener(new b(), CameraXExecutors.directExecutor());
        if (this.h.isEmpty()) {
            this.m.set(new ArrayList(this.i));
            return;
        }
        for (int i = 0; i < this.h.size(); i++) {
            this.i.add(null);
        }
        List<? extends ListenableFuture<? extends V>> list = this.h;
        for (int i2 = 0; i2 < list.size(); i2++) {
            ListenableFuture<? extends V> listenableFuture = list.get(i2);
            listenableFuture.addListener(new c(i2, listenableFuture), executor);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void e(int i, @NonNull Future<? extends V> future) {
        CallbackToFutureAdapter.Completer<List<V>> completer;
        ArrayList arrayList;
        int decrementAndGet;
        List<V> list = this.i;
        if (!isDone() && list != 0) {
            try {
                try {
                    try {
                        try {
                            Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                            list.set(i, Futures.getUninterruptibly(future));
                            decrementAndGet = this.k.decrementAndGet();
                            Preconditions.checkState(decrementAndGet >= 0, "Less than 0 remaining futures");
                        } catch (ExecutionException e) {
                            if (this.j) {
                                this.m.setException(e.getCause());
                            }
                            int decrementAndGet2 = this.k.decrementAndGet();
                            Preconditions.checkState(decrementAndGet2 >= 0, "Less than 0 remaining futures");
                            if (decrementAndGet2 != 0) {
                                return;
                            }
                            List<V> list2 = this.i;
                            if (list2 != null) {
                                completer = this.m;
                                arrayList = new ArrayList(list2);
                            }
                        }
                    } catch (RuntimeException e2) {
                        if (this.j) {
                            this.m.setException(e2);
                        }
                        int decrementAndGet3 = this.k.decrementAndGet();
                        Preconditions.checkState(decrementAndGet3 >= 0, "Less than 0 remaining futures");
                        if (decrementAndGet3 != 0) {
                            return;
                        }
                        List<V> list3 = this.i;
                        if (list3 != null) {
                            completer = this.m;
                            arrayList = new ArrayList(list3);
                        }
                    }
                } catch (Error e3) {
                    this.m.setException(e3);
                    int decrementAndGet4 = this.k.decrementAndGet();
                    Preconditions.checkState(decrementAndGet4 >= 0, "Less than 0 remaining futures");
                    if (decrementAndGet4 != 0) {
                        return;
                    }
                    List<V> list4 = this.i;
                    if (list4 != null) {
                        completer = this.m;
                        arrayList = new ArrayList(list4);
                    }
                } catch (CancellationException unused) {
                    if (this.j) {
                        cancel(false);
                    }
                    int decrementAndGet5 = this.k.decrementAndGet();
                    Preconditions.checkState(decrementAndGet5 >= 0, "Less than 0 remaining futures");
                    if (decrementAndGet5 != 0) {
                        return;
                    }
                    List<V> list5 = this.i;
                    if (list5 != null) {
                        completer = this.m;
                        arrayList = new ArrayList(list5);
                    }
                }
                if (decrementAndGet == 0) {
                    List<V> list6 = this.i;
                    if (list6 != null) {
                        completer = this.m;
                        arrayList = new ArrayList(list6);
                        completer.set(arrayList);
                        return;
                    }
                    Preconditions.checkState(isDone());
                    return;
                }
                return;
            } catch (Throwable th) {
                int decrementAndGet6 = this.k.decrementAndGet();
                Preconditions.checkState(decrementAndGet6 >= 0, "Less than 0 remaining futures");
                if (decrementAndGet6 == 0) {
                    List<V> list7 = this.i;
                    if (list7 != null) {
                        this.m.set(new ArrayList(list7));
                    } else {
                        Preconditions.checkState(isDone());
                    }
                }
                throw th;
            }
        }
        Preconditions.checkState(this.j, "Future was done before all dependencies completed");
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.l.isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.l.isDone();
    }
}
