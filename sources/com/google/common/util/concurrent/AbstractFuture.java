package com.google.common.util.concurrent;

import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.healthbuddies.RequestStatus;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.j2objc.annotations.ReflectionSupport;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import sun.misc.Unsafe;
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class AbstractFuture<V> extends InternalFutureFailureAccess implements ListenableFuture<V> {
    public static final boolean k;
    public static final Logger l;
    public static final b m;
    public static final Object n;
    @NullableDecl
    public volatile Object h;
    @NullableDecl
    public volatile e i;
    @NullableDecl
    public volatile l j;

    /* loaded from: classes10.dex */
    public static abstract class b {
        public b() {
        }

        public abstract boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2);

        public abstract boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2);

        public abstract boolean c(AbstractFuture<?> abstractFuture, l lVar, l lVar2);

        public abstract void d(l lVar, l lVar2);

        public abstract void e(l lVar, Thread thread);
    }

    /* loaded from: classes10.dex */
    public static final class c {
        public static final c c;
        public static final c d;

        /* renamed from: a  reason: collision with root package name */
        public final boolean f10741a;
        @NullableDecl
        public final Throwable b;

        static {
            if (AbstractFuture.k) {
                d = null;
                c = null;
                return;
            }
            d = new c(false, null);
            c = new c(true, null);
        }

        public c(boolean z, @NullableDecl Throwable th) {
            this.f10741a = z;
            this.b = th;
        }
    }

    /* loaded from: classes10.dex */
    public static final class d {
        public static final d b = new d(new a("Failure occurred while trying to finish a future."));

        /* renamed from: a  reason: collision with root package name */
        public final Throwable f10742a;

        /* loaded from: classes10.dex */
        public class a extends Throwable {
            public a(String str) {
                super(str);
            }

            @Override // java.lang.Throwable
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        }

        public d(Throwable th) {
            this.f10742a = (Throwable) Preconditions.checkNotNull(th);
        }
    }

    /* loaded from: classes10.dex */
    public static final class e {
        public static final e d = new e(null, null);

        /* renamed from: a  reason: collision with root package name */
        public final Runnable f10743a;
        public final Executor b;
        @NullableDecl
        public e c;

        public e(Runnable runnable, Executor executor) {
            this.f10743a = runnable;
            this.b = executor;
        }
    }

    /* loaded from: classes10.dex */
    public static final class f extends b {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicReferenceFieldUpdater<l, Thread> f10744a;
        public final AtomicReferenceFieldUpdater<l, l> b;
        public final AtomicReferenceFieldUpdater<AbstractFuture, l> c;
        public final AtomicReferenceFieldUpdater<AbstractFuture, e> d;
        public final AtomicReferenceFieldUpdater<AbstractFuture, Object> e;

        public f(AtomicReferenceFieldUpdater<l, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<l, l> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, l> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractFuture, e> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.f10744a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2) {
            return this.d.compareAndSet(abstractFuture, eVar, eVar2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return this.e.compareAndSet(abstractFuture, obj, obj2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean c(AbstractFuture<?> abstractFuture, l lVar, l lVar2) {
            return this.c.compareAndSet(abstractFuture, lVar, lVar2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public void d(l lVar, l lVar2) {
            this.b.lazySet(lVar, lVar2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public void e(l lVar, Thread thread) {
            this.f10744a.lazySet(lVar, thread);
        }
    }

    /* loaded from: classes10.dex */
    public static final class g<V> implements Runnable {
        public final AbstractFuture<V> h;
        public final ListenableFuture<? extends V> i;

        public g(AbstractFuture<V> abstractFuture, ListenableFuture<? extends V> listenableFuture) {
            this.h = abstractFuture;
            this.i = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.h.h != this) {
                return;
            }
            if (AbstractFuture.m.b(this.h, this, AbstractFuture.t(this.i))) {
                AbstractFuture.q(this.h);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class h extends b {
        public h() {
            super();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2) {
            synchronized (abstractFuture) {
                if (abstractFuture.i == eVar) {
                    abstractFuture.i = eVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            synchronized (abstractFuture) {
                if (abstractFuture.h == obj) {
                    abstractFuture.h = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean c(AbstractFuture<?> abstractFuture, l lVar, l lVar2) {
            synchronized (abstractFuture) {
                if (abstractFuture.j == lVar) {
                    abstractFuture.j = lVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public void d(l lVar, l lVar2) {
            lVar.b = lVar2;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public void e(l lVar, Thread thread) {
            lVar.f10746a = thread;
        }
    }

    /* loaded from: classes10.dex */
    public interface i<V> extends ListenableFuture<V> {
    }

    /* loaded from: classes10.dex */
    public static abstract class j<V> extends AbstractFuture<V> implements i<V> {
        @Override // com.google.common.util.concurrent.AbstractFuture, com.google.common.util.concurrent.ListenableFuture
        public final void addListener(Runnable runnable, Executor executor) {
            super.addListener(runnable, executor);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final boolean cancel(boolean z) {
            return super.cancel(z);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final V get() throws InterruptedException, ExecutionException {
            return (V) super.get();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isCancelled() {
            return super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        public final boolean isDone() {
            return super.isDone();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture, java.util.concurrent.Future
        @CanIgnoreReturnValue
        public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (V) super.get(j, timeUnit);
        }
    }

    /* loaded from: classes10.dex */
    public static final class k extends b {

        /* renamed from: a  reason: collision with root package name */
        public static final Unsafe f10745a;
        public static final long b;
        public static final long c;
        public static final long d;
        public static final long e;
        public static final long f;

        /* loaded from: classes10.dex */
        public class a implements PrivilegedExceptionAction<Unsafe> {
            @Override // java.security.PrivilegedExceptionAction
            /* renamed from: a */
            public Unsafe run() throws Exception {
                Field[] declaredFields;
                for (Field field : Unsafe.class.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object obj = field.get(null);
                    if (Unsafe.class.isInstance(obj)) {
                        return (Unsafe) Unsafe.class.cast(obj);
                    }
                }
                throw new NoSuchFieldError("the Unsafe");
            }
        }

        static {
            Unsafe unsafe;
            try {
                try {
                    unsafe = Unsafe.getUnsafe();
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            } catch (SecurityException unused) {
                unsafe = (Unsafe) AccessController.doPrivileged(new a());
            }
            try {
                c = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("j"));
                b = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("i"));
                d = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("h"));
                e = unsafe.objectFieldOffset(l.class.getDeclaredField("a"));
                f = unsafe.objectFieldOffset(l.class.getDeclaredField("b"));
                f10745a = unsafe;
            } catch (Exception e3) {
                Throwables.throwIfUnchecked(e3);
                throw new RuntimeException(e3);
            }
        }

        public k() {
            super();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2) {
            return f10745a.compareAndSwapObject(abstractFuture, b, eVar, eVar2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return f10745a.compareAndSwapObject(abstractFuture, d, obj, obj2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public boolean c(AbstractFuture<?> abstractFuture, l lVar, l lVar2) {
            return f10745a.compareAndSwapObject(abstractFuture, c, lVar, lVar2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public void d(l lVar, l lVar2) {
            f10745a.putObject(lVar, f, lVar2);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture.b
        public void e(l lVar, Thread thread) {
            f10745a.putObject(lVar, e, thread);
        }
    }

    /* loaded from: classes10.dex */
    public static final class l {
        public static final l c = new l(false);
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        public volatile Thread f10746a;
        @NullableDecl
        public volatile l b;

        public l(boolean z) {
        }

        public void a(l lVar) {
            AbstractFuture.m.d(this, lVar);
        }

        public void b() {
            Thread thread = this.f10746a;
            if (thread != null) {
                this.f10746a = null;
                LockSupport.unpark(thread);
            }
        }

        public l() {
            AbstractFuture.m.e(this, Thread.currentThread());
        }
    }

    static {
        boolean z;
        b hVar;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        k = z;
        l = Logger.getLogger(AbstractFuture.class.getName());
        Throwable th = null;
        try {
            hVar = new k();
            th = null;
        } catch (Throwable th2) {
            th = th2;
            try {
                hVar = new f(AtomicReferenceFieldUpdater.newUpdater(l.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(l.class, l.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, l.class, "j"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, e.class, "i"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Object.class, "h"));
            } catch (Throwable th3) {
                hVar = new h();
                th = th3;
            }
        }
        m = hVar;
        if (th != null) {
            Logger logger = l;
            Level level = Level.SEVERE;
            logger.log(level, "UnsafeAtomicHelper is broken!", th);
            logger.log(level, "SafeAtomicHelper is broken!", th);
        }
        n = new Object();
    }

    private void k(StringBuilder sb) {
        try {
            Object u = u(this);
            sb.append("SUCCESS, result=[");
            m(sb, u);
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append(RequestStatus.CANCELLED);
        } catch (RuntimeException e2) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e2.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e3) {
            sb.append("FAILURE, cause=[");
            sb.append(e3.getCause());
            sb.append("]");
        }
    }

    public static CancellationException o(@NullableDecl String str, @NullableDecl Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.common.util.concurrent.AbstractFuture$b] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.common.util.concurrent.AbstractFuture<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.google.common.util.concurrent.AbstractFuture] */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.google.common.util.concurrent.AbstractFuture<V>, com.google.common.util.concurrent.AbstractFuture] */
    public static void q(AbstractFuture<?> abstractFuture) {
        e eVar = null;
        while (true) {
            abstractFuture.w();
            abstractFuture.afterDone();
            e p = abstractFuture.p(eVar);
            while (p != null) {
                eVar = p.c;
                Runnable runnable = p.f10743a;
                if (runnable instanceof g) {
                    g gVar = (g) runnable;
                    abstractFuture = gVar.h;
                    if (abstractFuture.h == gVar) {
                        if (m.b(abstractFuture, gVar, t(gVar.i))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    r(runnable, p.b);
                }
                p = eVar;
            }
            return;
        }
    }

    public static void r(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            Logger logger = l;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            StringBuilder sb = new StringBuilder(valueOf.length() + 57 + valueOf2.length());
            sb.append("RuntimeException while executing runnable ");
            sb.append(valueOf);
            sb.append(" with executor ");
            sb.append(valueOf2);
            logger.log(level, sb.toString(), (Throwable) e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private V s(Object obj) throws ExecutionException {
        if (!(obj instanceof c)) {
            if (!(obj instanceof d)) {
                if (obj == n) {
                    return null;
                }
                return obj;
            }
            throw new ExecutionException(((d) obj).f10742a);
        }
        throw o("Task was cancelled.", ((c) obj).b);
    }

    public static Object t(ListenableFuture<?> listenableFuture) {
        Throwable tryInternalFastPathGetFailure;
        if (listenableFuture instanceof i) {
            Object obj = ((AbstractFuture) listenableFuture).h;
            if (obj instanceof c) {
                c cVar = (c) obj;
                if (cVar.f10741a) {
                    if (cVar.b != null) {
                        return new c(false, cVar.b);
                    }
                    return c.d;
                }
                return obj;
            }
            return obj;
        } else if ((listenableFuture instanceof InternalFutureFailureAccess) && (tryInternalFastPathGetFailure = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) listenableFuture)) != null) {
            return new d(tryInternalFastPathGetFailure);
        } else {
            boolean isCancelled = listenableFuture.isCancelled();
            if ((!k) & isCancelled) {
                return c.d;
            }
            try {
                Object u = u(listenableFuture);
                if (!isCancelled) {
                    return u == null ? n : u;
                }
                String valueOf = String.valueOf(listenableFuture);
                StringBuilder sb = new StringBuilder(valueOf.length() + 84);
                sb.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                sb.append(valueOf);
                return new c(false, new IllegalArgumentException(sb.toString()));
            } catch (CancellationException e2) {
                if (!isCancelled) {
                    String valueOf2 = String.valueOf(listenableFuture);
                    StringBuilder sb2 = new StringBuilder(valueOf2.length() + 77);
                    sb2.append("get() threw CancellationException, despite reporting isCancelled() == false: ");
                    sb2.append(valueOf2);
                    return new d(new IllegalArgumentException(sb2.toString(), e2));
                }
                return new c(false, e2);
            } catch (ExecutionException e3) {
                if (isCancelled) {
                    String valueOf3 = String.valueOf(listenableFuture);
                    StringBuilder sb3 = new StringBuilder(valueOf3.length() + 84);
                    sb3.append("get() did not throw CancellationException, despite reporting isCancelled() == true: ");
                    sb3.append(valueOf3);
                    return new c(false, new IllegalArgumentException(sb3.toString(), e3));
                }
                return new d(e3.getCause());
            } catch (Throwable th) {
                return new d(th);
            }
        }
    }

    public static <V> V u(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    private void w() {
        l lVar;
        do {
            lVar = this.j;
        } while (!m.c(this, lVar, l.c));
        while (lVar != null) {
            lVar.b();
            lVar = lVar.b;
        }
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable runnable, Executor executor) {
        e eVar;
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        if (!isDone() && (eVar = this.i) != e.d) {
            e eVar2 = new e(runnable, executor);
            do {
                eVar2.c = eVar;
                if (m.a(this, eVar, eVar2)) {
                    return;
                }
                eVar = this.i;
            } while (eVar != e.d);
            r(runnable, executor);
        }
        r(runnable, executor);
    }

    @Beta
    @ForOverride
    public void afterDone() {
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        c cVar;
        Object obj = this.h;
        if (!(obj == null) && !(obj instanceof g)) {
            return false;
        }
        if (k) {
            cVar = new c(z, new CancellationException("Future.cancel() was called."));
        } else if (z) {
            cVar = c.c;
        } else {
            cVar = c.d;
        }
        AbstractFuture<V> abstractFuture = this;
        boolean z2 = false;
        while (true) {
            if (m.b(abstractFuture, obj, cVar)) {
                if (z) {
                    abstractFuture.interruptTask();
                }
                q(abstractFuture);
                if (!(obj instanceof g)) {
                    return true;
                }
                ListenableFuture<? extends V> listenableFuture = ((g) obj).i;
                if (listenableFuture instanceof i) {
                    abstractFuture = (AbstractFuture) listenableFuture;
                    obj = abstractFuture.h;
                    if (!(obj == null) && !(obj instanceof g)) {
                        return true;
                    }
                    z2 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj = abstractFuture.h;
                if (!(obj instanceof g)) {
                    return z2;
                }
            }
        }
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get(long j2, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j2);
        if (!Thread.interrupted()) {
            Object obj = this.h;
            if ((obj != null) & (!(obj instanceof g))) {
                return s(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                l lVar = this.j;
                if (lVar != l.c) {
                    l lVar2 = new l();
                    do {
                        lVar2.a(lVar);
                        if (m.c(this, lVar, lVar2)) {
                            do {
                                r.a(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.h;
                                    if ((obj2 != null) & (!(obj2 instanceof g))) {
                                        return s(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    x(lVar2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            x(lVar2);
                        } else {
                            lVar = this.j;
                        }
                    } while (lVar != l.c);
                    return s(this.h);
                }
                return s(this.h);
            }
            while (nanos > 0) {
                Object obj3 = this.h;
                if ((obj3 != null) & (!(obj3 instanceof g))) {
                    return s(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String abstractFuture = toString();
            String timeUnit2 = timeUnit.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = timeUnit2.toLowerCase(locale);
            String lowerCase2 = timeUnit.toString().toLowerCase(locale);
            StringBuilder sb = new StringBuilder(String.valueOf(lowerCase2).length() + 28);
            sb.append("Waited ");
            sb.append(j2);
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(lowerCase2);
            String sb2 = sb.toString();
            if (nanos + 1000 < 0) {
                String concat = String.valueOf(sb2).concat(" (plus ");
                long j3 = -nanos;
                long convert = timeUnit.convert(j3, TimeUnit.NANOSECONDS);
                long nanos2 = j3 - timeUnit.toNanos(convert);
                int i2 = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
                boolean z = i2 == 0 || nanos2 > 1000;
                if (i2 > 0) {
                    String valueOf = String.valueOf(concat);
                    StringBuilder sb3 = new StringBuilder(valueOf.length() + 21 + String.valueOf(lowerCase).length());
                    sb3.append(valueOf);
                    sb3.append(convert);
                    sb3.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    sb3.append(lowerCase);
                    String sb4 = sb3.toString();
                    if (z) {
                        sb4 = String.valueOf(sb4).concat(Constants.SEPARATOR_COMMA);
                    }
                    concat = String.valueOf(sb4).concat(HexStringBuilder.DEFAULT_SEPARATOR);
                }
                if (z) {
                    String valueOf2 = String.valueOf(concat);
                    StringBuilder sb5 = new StringBuilder(valueOf2.length() + 33);
                    sb5.append(valueOf2);
                    sb5.append(nanos2);
                    sb5.append(" nanoseconds ");
                    concat = sb5.toString();
                }
                sb2 = String.valueOf(concat).concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(String.valueOf(sb2).concat(" but future completed as timeout expired"));
            }
            StringBuilder sb6 = new StringBuilder(String.valueOf(sb2).length() + 5 + String.valueOf(abstractFuture).length());
            sb6.append(sb2);
            sb6.append(" for ");
            sb6.append(abstractFuture);
            throw new TimeoutException(sb6.toString());
        }
        throw new InterruptedException();
    }

    public void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.h instanceof c;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        Object obj = this.h;
        return (!(obj instanceof g)) & (obj != null);
    }

    public final void l(StringBuilder sb) {
        String sb2;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.h;
        if (obj instanceof g) {
            sb.append(", setFuture=[");
            n(sb, ((g) obj).i);
            sb.append("]");
        } else {
            try {
                sb2 = Strings.emptyToNull(pendingToString());
            } catch (RuntimeException | StackOverflowError e2) {
                String valueOf = String.valueOf(e2.getClass());
                StringBuilder sb3 = new StringBuilder(valueOf.length() + 38);
                sb3.append("Exception thrown from implementation: ");
                sb3.append(valueOf);
                sb2 = sb3.toString();
            }
            if (sb2 != null) {
                sb.append(", info=[");
                sb.append(sb2);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            k(sb);
        }
    }

    public final void m(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("null");
        } else if (obj == this) {
            sb.append("this future");
        } else {
            sb.append(obj.getClass().getName());
            sb.append("@");
            sb.append(Integer.toHexString(System.identityHashCode(obj)));
        }
    }

    public final void n(StringBuilder sb, Object obj) {
        try {
            if (obj == this) {
                sb.append("this future");
            } else {
                sb.append(obj);
            }
        } catch (RuntimeException | StackOverflowError e2) {
            sb.append("Exception thrown from implementation: ");
            sb.append(e2.getClass());
        }
    }

    public final e p(e eVar) {
        e eVar2;
        do {
            eVar2 = this.i;
        } while (!m.a(this, eVar2, e.d));
        e eVar3 = eVar;
        e eVar4 = eVar2;
        while (eVar4 != null) {
            e eVar5 = eVar4.c;
            eVar4.c = eVar3;
            eVar3 = eVar4;
            eVar4 = eVar5;
        }
        return eVar3;
    }

    @NullableDecl
    public String pendingToString() {
        if (this instanceof ScheduledFuture) {
            long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
            StringBuilder sb = new StringBuilder(41);
            sb.append("remaining delay=[");
            sb.append(delay);
            sb.append(" ms]");
            return sb.toString();
        }
        return null;
    }

    @CanIgnoreReturnValue
    public boolean set(@NullableDecl V v) {
        if (v == null) {
            v = (V) n;
        }
        if (m.b(this, null, v)) {
            q(this);
            return true;
        }
        return false;
    }

    @CanIgnoreReturnValue
    public boolean setException(Throwable th) {
        if (m.b(this, null, new d((Throwable) Preconditions.checkNotNull(th)))) {
            q(this);
            return true;
        }
        return false;
    }

    @CanIgnoreReturnValue
    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        d dVar;
        Preconditions.checkNotNull(listenableFuture);
        Object obj = this.h;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (m.b(this, null, t(listenableFuture))) {
                    q(this);
                    return true;
                }
                return false;
            }
            g gVar = new g(this, listenableFuture);
            if (m.b(this, null, gVar)) {
                try {
                    listenableFuture.addListener(gVar, com.google.common.util.concurrent.g.INSTANCE);
                } catch (Throwable th) {
                    try {
                        dVar = new d(th);
                    } catch (Throwable unused) {
                        dVar = d.b;
                    }
                    m.b(this, gVar, dVar);
                }
                return true;
            }
            obj = this.h;
        }
        if (obj instanceof c) {
            listenableFuture.cancel(((c) obj).f10741a);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (isCancelled()) {
            sb.append(RequestStatus.CANCELLED);
        } else if (isDone()) {
            k(sb);
        } else {
            l(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.common.util.concurrent.internal.InternalFutureFailureAccess
    @NullableDecl
    public final Throwable tryInternalFastPathGetFailure() {
        if (this instanceof i) {
            Object obj = this.h;
            if (obj instanceof d) {
                return ((d) obj).f10742a;
            }
            return null;
        }
        return null;
    }

    public final void v(@NullableDecl Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(wasInterrupted());
        }
    }

    public final boolean wasInterrupted() {
        Object obj = this.h;
        return (obj instanceof c) && ((c) obj).f10741a;
    }

    public final void x(l lVar) {
        lVar.f10746a = null;
        while (true) {
            l lVar2 = this.j;
            if (lVar2 == l.c) {
                return;
            }
            l lVar3 = null;
            while (lVar2 != null) {
                l lVar4 = lVar2.b;
                if (lVar2.f10746a != null) {
                    lVar3 = lVar2;
                } else if (lVar3 != null) {
                    lVar3.b = lVar4;
                    if (lVar3.f10746a == null) {
                        break;
                    }
                } else if (!m.c(this, lVar2, lVar4)) {
                    break;
                }
                lVar2 = lVar4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    @CanIgnoreReturnValue
    public V get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.h;
            if ((obj2 != null) & (!(obj2 instanceof g))) {
                return s(obj2);
            }
            l lVar = this.j;
            if (lVar != l.c) {
                l lVar2 = new l();
                do {
                    lVar2.a(lVar);
                    if (m.c(this, lVar, lVar2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.h;
                            } else {
                                x(lVar2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof g))));
                        return s(obj);
                    }
                    lVar = this.j;
                } while (lVar != l.c);
                return s(this.h);
            }
            return s(this.h);
        }
        throw new InterruptedException();
    }
}
