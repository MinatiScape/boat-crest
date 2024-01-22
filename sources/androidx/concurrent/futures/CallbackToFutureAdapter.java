package androidx.concurrent.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public final class CallbackToFutureAdapter {

    /* loaded from: classes.dex */
    public static final class Completer<T> {

        /* renamed from: a  reason: collision with root package name */
        public Object f850a;
        public b<T> b;
        public ResolvableFuture<Void> c = ResolvableFuture.create();
        public boolean d;

        public void a() {
            this.f850a = null;
            this.b = null;
            this.c.set(null);
        }

        public void addCancellationListener(@NonNull Runnable runnable, @NonNull Executor executor) {
            ResolvableFuture<Void> resolvableFuture = this.c;
            if (resolvableFuture != null) {
                resolvableFuture.addListener(runnable, executor);
            }
        }

        public final void b() {
            this.f850a = null;
            this.b = null;
            this.c = null;
        }

        public void finalize() {
            ResolvableFuture<Void> resolvableFuture;
            b<T> bVar = this.b;
            if (bVar != null && !bVar.isDone()) {
                bVar.c(new a("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f850a));
            }
            if (this.d || (resolvableFuture = this.c) == null) {
                return;
            }
            resolvableFuture.set(null);
        }

        public boolean set(T t) {
            boolean z = true;
            this.d = true;
            b<T> bVar = this.b;
            z = (bVar == null || !bVar.b(t)) ? false : false;
            if (z) {
                b();
            }
            return z;
        }

        public boolean setCancelled() {
            boolean z = true;
            this.d = true;
            b<T> bVar = this.b;
            z = (bVar == null || !bVar.a(true)) ? false : false;
            if (z) {
                b();
            }
            return z;
        }

        public boolean setException(@NonNull Throwable th) {
            boolean z = true;
            this.d = true;
            b<T> bVar = this.b;
            z = (bVar == null || !bVar.c(th)) ? false : false;
            if (z) {
                b();
            }
            return z;
        }
    }

    /* loaded from: classes.dex */
    public interface Resolver<T> {
        @Nullable
        Object attachCompleter(@NonNull Completer<T> completer) throws Exception;
    }

    /* loaded from: classes.dex */
    public static final class a extends Throwable {
        public a(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class b<T> implements ListenableFuture<T> {
        public final WeakReference<Completer<T>> h;
        public final AbstractResolvableFuture<T> i = new a();

        /* loaded from: classes.dex */
        public class a extends AbstractResolvableFuture<Object> {
            public a() {
            }

            @Override // androidx.concurrent.futures.AbstractResolvableFuture
            public String pendingToString() {
                Completer<T> completer = b.this.h.get();
                if (completer == null) {
                    return "Completer object has been garbage collected, future will fail soon";
                }
                return "tag=[" + completer.f850a + "]";
            }
        }

        public b(Completer<T> completer) {
            this.h = new WeakReference<>(completer);
        }

        public boolean a(boolean z) {
            return this.i.cancel(z);
        }

        @Override // com.google.common.util.concurrent.ListenableFuture
        public void addListener(@NonNull Runnable runnable, @NonNull Executor executor) {
            this.i.addListener(runnable, executor);
        }

        public boolean b(T t) {
            return this.i.set(t);
        }

        public boolean c(Throwable th) {
            return this.i.setException(th);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            Completer<T> completer = this.h.get();
            boolean cancel = this.i.cancel(z);
            if (cancel && completer != null) {
                completer.a();
            }
            return cancel;
        }

        @Override // java.util.concurrent.Future
        public T get() throws InterruptedException, ExecutionException {
            return this.i.get();
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.i.isCancelled();
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.i.isDone();
        }

        public String toString() {
            return this.i.toString();
        }

        @Override // java.util.concurrent.Future
        public T get(long j, @NonNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.i.get(j, timeUnit);
        }
    }

    @NonNull
    public static <T> ListenableFuture<T> getFuture(@NonNull Resolver<T> resolver) {
        Completer<T> completer = new Completer<>();
        b<T> bVar = new b<>(completer);
        completer.b = bVar;
        completer.f850a = resolver.getClass();
        try {
            Object attachCompleter = resolver.attachCompleter(completer);
            if (attachCompleter != null) {
                completer.f850a = attachCompleter;
            }
        } catch (Exception e) {
            bVar.c(e);
        }
        return bVar;
    }
}
