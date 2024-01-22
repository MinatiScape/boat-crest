package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.errorprone.annotations.ForOverride;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class b<I, O, F, T> extends FluentFuture.a<O> implements Runnable {
    @NullableDecl
    public ListenableFuture<? extends I> o;
    @NullableDecl
    public F p;

    /* loaded from: classes10.dex */
    public static final class a<I, O> extends b<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        public a(ListenableFuture<? extends I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
            super(listenableFuture, asyncFunction);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.b
        /* renamed from: C */
        public ListenableFuture<? extends O> A(AsyncFunction<? super I, ? extends O> asyncFunction, @NullableDecl I i) throws Exception {
            ListenableFuture<? extends O> apply = asyncFunction.apply(i);
            Preconditions.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", asyncFunction);
            return apply;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.b
        /* renamed from: D */
        public void B(ListenableFuture<? extends O> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    /* renamed from: com.google.common.util.concurrent.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0528b<I, O> extends b<I, O, Function<? super I, ? extends O>, O> {
        public C0528b(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        @Override // com.google.common.util.concurrent.b
        public void B(@NullableDecl O o) {
            set(o);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.b
        @NullableDecl
        /* renamed from: C */
        public O A(Function<? super I, ? extends O> function, @NullableDecl I i) {
            return function.apply(i);
        }
    }

    public b(ListenableFuture<? extends I> listenableFuture, F f) {
        this.o = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.p = (F) Preconditions.checkNotNull(f);
    }

    public static <I, O> ListenableFuture<O> y(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        C0528b c0528b = new C0528b(listenableFuture, function);
        listenableFuture.addListener(c0528b, MoreExecutors.d(executor, c0528b));
        return c0528b;
    }

    public static <I, O> ListenableFuture<O> z(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        Preconditions.checkNotNull(executor);
        a aVar = new a(listenableFuture, asyncFunction);
        listenableFuture.addListener(aVar, MoreExecutors.d(executor, aVar));
        return aVar;
    }

    @NullableDecl
    @ForOverride
    public abstract T A(F f, @NullableDecl I i) throws Exception;

    @ForOverride
    public abstract void B(@NullableDecl T t);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        v(this.o);
        this.o = null;
        this.p = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String str;
        ListenableFuture<? extends I> listenableFuture = this.o;
        F f = this.p;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            String valueOf = String.valueOf(listenableFuture);
            StringBuilder sb = new StringBuilder(valueOf.length() + 16);
            sb.append("inputFuture=[");
            sb.append(valueOf);
            sb.append("], ");
            str = sb.toString();
        } else {
            str = "";
        }
        if (f == null) {
            if (pendingToString != null) {
                String valueOf2 = String.valueOf(str);
                return pendingToString.length() != 0 ? valueOf2.concat(pendingToString) : new String(valueOf2);
            }
            return null;
        }
        String valueOf3 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 11 + valueOf3.length());
        sb2.append(str);
        sb2.append("function=[");
        sb2.append(valueOf3);
        sb2.append("]");
        return sb2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> listenableFuture = this.o;
        F f = this.p;
        if ((isCancelled() | (listenableFuture == null)) || (f == null)) {
            return;
        }
        this.o = null;
        if (listenableFuture.isCancelled()) {
            setFuture(listenableFuture);
            return;
        }
        try {
            try {
                Object A = A(f, Futures.getDone(listenableFuture));
                this.p = null;
                B(A);
            } catch (Throwable th) {
                try {
                    setException(th);
                } finally {
                    this.p = null;
                }
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e2) {
            setException(e2);
        } catch (ExecutionException e3) {
            setException(e3.getCause());
        }
    }
}
