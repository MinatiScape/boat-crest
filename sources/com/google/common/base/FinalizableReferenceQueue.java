package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public class FinalizableReferenceQueue implements Closeable {
    public static final Logger k = Logger.getLogger(FinalizableReferenceQueue.class.getName());
    public static final Method l = c(d(new d(), new a(), new b()));
    public final ReferenceQueue<Object> h;
    public final PhantomReference<Object> i;
    public final boolean j;

    /* loaded from: classes10.dex */
    public static class a implements c {
        @Override // com.google.common.base.FinalizableReferenceQueue.c
        @NullableDecl
        public Class<?> a() {
            try {
                return c(b()).loadClass("com.google.common.base.internal.Finalizer");
            } catch (Exception e) {
                FinalizableReferenceQueue.k.log(Level.WARNING, "Could not load Finalizer in its own class loader. Loading Finalizer in the current class loader instead. As a result, you will not be able to garbage collect this class loader. To support reclaiming this class loader, either resolve the underlying issue, or move Guava to your system class path.", (Throwable) e);
                return null;
            }
        }

        public URL b() throws IOException {
            String concat = String.valueOf("com.google.common.base.internal.Finalizer".replace('.', '/')).concat(".class");
            URL resource = a.class.getClassLoader().getResource(concat);
            if (resource != null) {
                String url = resource.toString();
                if (!url.endsWith(concat)) {
                    throw new IOException(url.length() != 0 ? "Unsupported path style: ".concat(url) : new String("Unsupported path style: "));
                }
                return new URL(resource, url.substring(0, url.length() - concat.length()));
            }
            throw new FileNotFoundException(concat);
        }

        public URLClassLoader c(URL url) {
            return new URLClassLoader(new URL[]{url}, null);
        }
    }

    /* loaded from: classes10.dex */
    public static class b implements c {
        @Override // com.google.common.base.FinalizableReferenceQueue.c
        public Class<?> a() {
            try {
                return Class.forName("com.google.common.base.internal.Finalizer");
            } catch (ClassNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* loaded from: classes10.dex */
    public interface c {
        @NullableDecl
        Class<?> a();
    }

    /* loaded from: classes10.dex */
    public static class d implements c {
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        public static boolean f10518a;

        @Override // com.google.common.base.FinalizableReferenceQueue.c
        @NullableDecl
        public Class<?> a() {
            if (f10518a) {
                return null;
            }
            try {
                ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
                if (systemClassLoader != null) {
                    try {
                        return systemClassLoader.loadClass("com.google.common.base.internal.Finalizer");
                    } catch (ClassNotFoundException unused) {
                    }
                }
                return null;
            } catch (SecurityException unused2) {
                FinalizableReferenceQueue.k.info("Not allowed to access system class loader.");
                return null;
            }
        }
    }

    public FinalizableReferenceQueue() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        this.h = referenceQueue;
        PhantomReference<Object> phantomReference = new PhantomReference<>(this, referenceQueue);
        this.i = phantomReference;
        boolean z = true;
        try {
            l.invoke(null, FinalizableReference.class, referenceQueue, phantomReference);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (Throwable th) {
            k.log(Level.INFO, "Failed to start reference finalizer thread. Reference cleanup will only occur when new references are created.", th);
            z = false;
        }
        this.j = z;
    }

    public static Method c(Class<?> cls) {
        try {
            return cls.getMethod("startFinalizer", Class.class, ReferenceQueue.class, PhantomReference.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    public static Class<?> d(c... cVarArr) {
        for (c cVar : cVarArr) {
            Class<?> a2 = cVar.a();
            if (a2 != null) {
                return a2;
            }
        }
        throw new AssertionError();
    }

    public void b() {
        if (this.j) {
            return;
        }
        while (true) {
            Reference<? extends Object> poll = this.h.poll();
            if (poll == null) {
                return;
            }
            poll.clear();
            try {
                ((FinalizableReference) poll).finalizeReferent();
            } catch (Throwable th) {
                k.log(Level.SEVERE, "Error cleaning up after reference.", th);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.i.enqueue();
        b();
    }
}
