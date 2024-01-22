package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.Throwable;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes10.dex */
public abstract class a<V, X extends Throwable, F, T> extends FluentFuture.a<V> implements Runnable {
    @NullableDecl
    public ListenableFuture<? extends V> o;
    @NullableDecl
    public Class<X> p;
    @NullableDecl
    public F q;

    /* renamed from: com.google.common.util.concurrent.a$a  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public static final class C0527a<V, X extends Throwable> extends a<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        public C0527a(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction) {
            super(listenableFuture, cls, asyncFunction);
        }

        @Override // com.google.common.util.concurrent.a
        /* renamed from: C */
        public ListenableFuture<? extends V> A(AsyncFunction<? super X, ? extends V> asyncFunction, X x) throws Exception {
            ListenableFuture<? extends V> apply = asyncFunction.apply(x);
            Preconditions.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", asyncFunction);
            return apply;
        }

        @Override // com.google.common.util.concurrent.a
        /* renamed from: D */
        public void B(ListenableFuture<? extends V> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    /* loaded from: classes10.dex */
    public static final class b<V, X extends Throwable> extends a<V, X, Function<? super X, ? extends V>, V> {
        public b(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
            super(listenableFuture, cls, function);
        }

        @Override // com.google.common.util.concurrent.a
        public void B(@NullableDecl V v) {
            set(v);
        }

        @Override // com.google.common.util.concurrent.a
        @NullableDecl
        /* renamed from: C */
        public V A(Function<? super X, ? extends V> function, X x) throws Exception {
            return function.apply(x);
        }
    }

    public a(ListenableFuture<? extends V> listenableFuture, Class<X> cls, F f) {
        this.o = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.p = (Class) Preconditions.checkNotNull(cls);
        this.q = (F) Preconditions.checkNotNull(f);
    }

    public static <V, X extends Throwable> ListenableFuture<V> y(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        b bVar = new b(listenableFuture, cls, function);
        listenableFuture.addListener(bVar, MoreExecutors.d(executor, bVar));
        return bVar;
    }

    public static <X extends Throwable, V> ListenableFuture<V> z(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction, Executor executor) {
        C0527a c0527a = new C0527a(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(c0527a, MoreExecutors.d(executor, c0527a));
        return c0527a;
    }

    @NullableDecl
    @ForOverride
    public abstract T A(F f, X x) throws Exception;

    @ForOverride
    public abstract void B(@NullableDecl T t);

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        v(this.o);
        this.o = null;
        this.p = null;
        this.q = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String str;
        ListenableFuture<? extends V> listenableFuture = this.o;
        Class<X> cls = this.p;
        F f = this.q;
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
        if (cls == null || f == null) {
            if (pendingToString != null) {
                String valueOf2 = String.valueOf(str);
                return pendingToString.length() != 0 ? valueOf2.concat(pendingToString) : new String(valueOf2);
            }
            return null;
        }
        String valueOf3 = String.valueOf(cls);
        String valueOf4 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 29 + valueOf3.length() + valueOf4.length());
        sb2.append(str);
        sb2.append("exceptionType=[");
        sb2.append(valueOf3);
        sb2.append("], fallback=[");
        sb2.append(valueOf4);
        sb2.append("]");
        return sb2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Class<X extends java.lang.Throwable>, F] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r9 = this;
            com.google.common.util.concurrent.ListenableFuture<? extends V> r0 = r9.o
            java.lang.Class<X extends java.lang.Throwable> r1 = r9.p
            F r2 = r9.q
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Lc
            r5 = r3
            goto Ld
        Lc:
            r5 = r4
        Ld:
            if (r1 != 0) goto L11
            r6 = r3
            goto L12
        L11:
            r6 = r4
        L12:
            r5 = r5 | r6
            if (r2 != 0) goto L16
            goto L17
        L16:
            r3 = r4
        L17:
            r3 = r3 | r5
            if (r3 != 0) goto Lae
            boolean r3 = r9.isCancelled()
            if (r3 == 0) goto L22
            goto Lae
        L22:
            r3 = 0
            r9.o = r3
            boolean r4 = r0 instanceof com.google.common.util.concurrent.internal.InternalFutureFailureAccess     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            if (r4 == 0) goto L31
            r4 = r0
            com.google.common.util.concurrent.internal.InternalFutureFailureAccess r4 = (com.google.common.util.concurrent.internal.InternalFutureFailureAccess) r4     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            java.lang.Throwable r4 = com.google.common.util.concurrent.internal.InternalFutures.tryInternalFastPathGetFailure(r4)     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L32
        L31:
            r4 = r3
        L32:
            if (r4 != 0) goto L3a
            java.lang.Object r5 = com.google.common.util.concurrent.Futures.getDone(r0)     // Catch: java.lang.Throwable -> L39 java.util.concurrent.ExecutionException -> L3c
            goto L83
        L39:
            r4 = move-exception
        L3a:
            r5 = r3
            goto L83
        L3c:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L81
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r7 = r6.length()
            int r7 = r7 + 35
            int r8 = r4.length()
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r7)
            java.lang.String r7 = "Future type "
            r8.append(r7)
            r8.append(r6)
            java.lang.String r6 = " threw "
            r8.append(r6)
            r8.append(r4)
            java.lang.String r4 = " without a cause"
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r5.<init>(r4)
        L81:
            r4 = r5
            goto L3a
        L83:
            if (r4 != 0) goto L89
            r9.set(r5)
            return
        L89:
            boolean r1 = com.google.common.util.concurrent.s.a(r4, r1)
            if (r1 != 0) goto L93
            r9.setFuture(r0)
            return
        L93:
            java.lang.Object r0 = r9.A(r2, r4)     // Catch: java.lang.Throwable -> L9f
            r9.p = r3
            r9.q = r3
            r9.B(r0)
            return
        L9f:
            r0 = move-exception
            r9.setException(r0)     // Catch: java.lang.Throwable -> La8
            r9.p = r3
            r9.q = r3
            return
        La8:
            r0 = move-exception
            r9.p = r3
            r9.q = r3
            throw r0
        Lae:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.a.run():void");
    }
}
