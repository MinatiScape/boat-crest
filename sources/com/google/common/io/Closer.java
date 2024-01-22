package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class Closer implements Closeable {
    public static final c k;
    @VisibleForTesting
    public final c h;
    public final Deque<Closeable> i = new ArrayDeque(4);
    @NullableDecl
    public Throwable j;

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class a implements c {

        /* renamed from: a  reason: collision with root package name */
        public static final a f10675a = new a();

        @Override // com.google.common.io.Closer.c
        public void a(Closeable closeable, Throwable th, Throwable th2) {
            Logger logger = Closeables.f10674a;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(closeable);
            StringBuilder sb = new StringBuilder(valueOf.length() + 42);
            sb.append("Suppressing exception thrown when closing ");
            sb.append(valueOf);
            logger.log(level, sb.toString(), th2);
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static final class b implements c {

        /* renamed from: a  reason: collision with root package name */
        public static final b f10676a = new b();
        public static final Method b = b();

        public static Method b() {
            try {
                return Throwable.class.getMethod("addSuppressed", Throwable.class);
            } catch (Throwable unused) {
                return null;
            }
        }

        public static boolean c() {
            return b != null;
        }

        @Override // com.google.common.io.Closer.c
        public void a(Closeable closeable, Throwable th, Throwable th2) {
            if (th == th2) {
                return;
            }
            try {
                b.invoke(th, th2);
            } catch (Throwable unused) {
                a.f10675a.a(closeable, th, th2);
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    public interface c {
        void a(Closeable closeable, Throwable th, Throwable th2);
    }

    static {
        c cVar;
        if (b.c()) {
            cVar = b.f10676a;
        } else {
            cVar = a.f10675a;
        }
        k = cVar;
    }

    @VisibleForTesting
    public Closer(c cVar) {
        this.h = (c) Preconditions.checkNotNull(cVar);
    }

    public static Closer create() {
        return new Closer(k);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Throwable th = this.j;
        while (!this.i.isEmpty()) {
            Closeable removeFirst = this.i.removeFirst();
            try {
                removeFirst.close();
            } catch (Throwable th2) {
                if (th == null) {
                    th = th2;
                } else {
                    this.h.a(removeFirst, th, th2);
                }
            }
        }
        if (this.j != null || th == null) {
            return;
        }
        Throwables.propagateIfPossible(th, IOException.class);
        throw new AssertionError(th);
    }

    @CanIgnoreReturnValue
    public <C extends Closeable> C register(@NullableDecl C c2) {
        if (c2 != null) {
            this.i.addFirst(c2);
        }
        return c2;
    }

    public RuntimeException rethrow(Throwable th) throws IOException {
        Preconditions.checkNotNull(th);
        this.j = th;
        Throwables.propagateIfPossible(th, IOException.class);
        throw new RuntimeException(th);
    }

    public <X extends Exception> RuntimeException rethrow(Throwable th, Class<X> cls) throws IOException, Exception {
        Preconditions.checkNotNull(th);
        this.j = th;
        Throwables.propagateIfPossible(th, IOException.class);
        Throwables.propagateIfPossible(th, cls);
        throw new RuntimeException(th);
    }

    public <X1 extends Exception, X2 extends Exception> RuntimeException rethrow(Throwable th, Class<X1> cls, Class<X2> cls2) throws IOException, Exception, Exception {
        Preconditions.checkNotNull(th);
        this.j = th;
        Throwables.propagateIfPossible(th, IOException.class);
        Throwables.propagateIfPossible(th, cls, cls2);
        throw new RuntimeException(th);
    }
}
