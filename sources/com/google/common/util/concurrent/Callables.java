package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.concurrent.Callable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class Callables {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class a<T> implements Callable<T> {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        @Override // java.util.concurrent.Callable
        public T call() {
            return (T) this.h;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class b<T> implements AsyncCallable<T> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ListeningExecutorService f10759a;
        public final /* synthetic */ Callable b;

        public b(ListeningExecutorService listeningExecutorService, Callable callable) {
            this.f10759a = listeningExecutorService;
            this.b = callable;
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public ListenableFuture<T> call() throws Exception {
            return this.f10759a.submit((Callable) this.b);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes10.dex */
    public class c<T> implements Callable<T> {
        public final /* synthetic */ Supplier h;
        public final /* synthetic */ Callable i;

        public c(Supplier supplier, Callable callable) {
            this.h = supplier;
            this.i = callable;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            Thread currentThread = Thread.currentThread();
            String name = currentThread.getName();
            boolean d = Callables.d((String) this.h.get(), currentThread);
            try {
                return (T) this.i.call();
            } finally {
                if (d) {
                    Callables.d(name, currentThread);
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Runnable {
        public final /* synthetic */ Supplier h;
        public final /* synthetic */ Runnable i;

        public d(Supplier supplier, Runnable runnable) {
            this.h = supplier;
            this.i = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread currentThread = Thread.currentThread();
            String name = currentThread.getName();
            boolean d = Callables.d((String) this.h.get(), currentThread);
            try {
                this.i.run();
            } finally {
                if (d) {
                    Callables.d(name, currentThread);
                }
            }
        }
    }

    @Beta
    @GwtIncompatible
    public static <T> AsyncCallable<T> asAsyncCallable(Callable<T> callable, ListeningExecutorService listeningExecutorService) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(listeningExecutorService);
        return new b(listeningExecutorService, callable);
    }

    @GwtIncompatible
    public static Runnable b(Runnable runnable, Supplier<String> supplier) {
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(runnable);
        return new d(supplier, runnable);
    }

    @GwtIncompatible
    public static <T> Callable<T> c(Callable<T> callable, Supplier<String> supplier) {
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(callable);
        return new c(supplier, callable);
    }

    @GwtIncompatible
    public static boolean d(String str, Thread thread) {
        try {
            thread.setName(str);
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static <T> Callable<T> returning(@NullableDecl T t) {
        return new a(t);
    }
}
