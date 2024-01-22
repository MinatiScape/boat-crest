package androidx.work.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.healthbuddies.RequestStatus;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.Objects;
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
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class AbstractFuture<V> implements ListenableFuture<V> {
    public static final boolean k = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Logger l = Logger.getLogger(AbstractFuture.class.getName());
    public static final b m;
    public static final Object n;
    @Nullable
    public volatile Object h;
    @Nullable
    public volatile e i;
    @Nullable
    public volatile i j;

    /* loaded from: classes.dex */
    public static abstract class b {
        public b() {
        }

        public abstract boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2);

        public abstract boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2);

        public abstract boolean c(AbstractFuture<?> abstractFuture, i iVar, i iVar2);

        public abstract void d(i iVar, i iVar2);

        public abstract void e(i iVar, Thread thread);
    }

    /* loaded from: classes.dex */
    public static final class c {
        public static final c c;
        public static final c d;

        /* renamed from: a  reason: collision with root package name */
        public final boolean f1839a;
        @Nullable
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

        public c(boolean z, @Nullable Throwable th) {
            this.f1839a = z;
            this.b = th;
        }
    }

    /* loaded from: classes.dex */
    public static final class d {
        public static final d b = new d(new a("Failure occurred while trying to finish a future."));

        /* renamed from: a  reason: collision with root package name */
        public final Throwable f1840a;

        /* loaded from: classes.dex */
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
            this.f1840a = (Throwable) AbstractFuture.c(th);
        }
    }

    /* loaded from: classes.dex */
    public static final class e {
        public static final e d = new e(null, null);

        /* renamed from: a  reason: collision with root package name */
        public final Runnable f1841a;
        public final Executor b;
        @Nullable
        public e c;

        public e(Runnable runnable, Executor executor) {
            this.f1841a = runnable;
            this.b = executor;
        }
    }

    /* loaded from: classes.dex */
    public static final class f extends b {

        /* renamed from: a  reason: collision with root package name */
        public final AtomicReferenceFieldUpdater<i, Thread> f1842a;
        public final AtomicReferenceFieldUpdater<i, i> b;
        public final AtomicReferenceFieldUpdater<AbstractFuture, i> c;
        public final AtomicReferenceFieldUpdater<AbstractFuture, e> d;
        public final AtomicReferenceFieldUpdater<AbstractFuture, Object> e;

        public f(AtomicReferenceFieldUpdater<i, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<i, i> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, i> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractFuture, e> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.f1842a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2) {
            return this.d.compareAndSet(abstractFuture, eVar, eVar2);
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return this.e.compareAndSet(abstractFuture, obj, obj2);
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public boolean c(AbstractFuture<?> abstractFuture, i iVar, i iVar2) {
            return this.c.compareAndSet(abstractFuture, iVar, iVar2);
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public void d(i iVar, i iVar2) {
            this.b.lazySet(iVar, iVar2);
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public void e(i iVar, Thread thread) {
            this.f1842a.lazySet(iVar, thread);
        }
    }

    /* loaded from: classes.dex */
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
            if (AbstractFuture.m.b(this.h, this, AbstractFuture.h(this.i))) {
                AbstractFuture.e(this.h);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class h extends b {
        public h() {
            super();
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public boolean a(AbstractFuture<?> abstractFuture, e eVar, e eVar2) {
            synchronized (abstractFuture) {
                if (abstractFuture.i == eVar) {
                    abstractFuture.i = eVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public boolean b(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            synchronized (abstractFuture) {
                if (abstractFuture.h == obj) {
                    abstractFuture.h = obj2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public boolean c(AbstractFuture<?> abstractFuture, i iVar, i iVar2) {
            synchronized (abstractFuture) {
                if (abstractFuture.j == iVar) {
                    abstractFuture.j = iVar2;
                    return true;
                }
                return false;
            }
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public void d(i iVar, i iVar2) {
            iVar.b = iVar2;
        }

        @Override // androidx.work.impl.utils.futures.AbstractFuture.b
        public void e(i iVar, Thread thread) {
            iVar.f1843a = thread;
        }
    }

    /* loaded from: classes.dex */
    public static final class i {
        public static final i c = new i(false);
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public volatile Thread f1843a;
        @Nullable
        public volatile i b;

        public i(boolean z) {
        }

        public void a(i iVar) {
            AbstractFuture.m.d(this, iVar);
        }

        public void b() {
            Thread thread = this.f1843a;
            if (thread != null) {
                this.f1843a = null;
                LockSupport.unpark(thread);
            }
        }

        public i() {
            AbstractFuture.m.e(this, Thread.currentThread());
        }
    }

    static {
        b hVar;
        try {
            hVar = new f(AtomicReferenceFieldUpdater.newUpdater(i.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(i.class, i.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, i.class, "j"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, e.class, "i"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Object.class, "h"));
            th = null;
        } catch (Throwable th) {
            th = th;
            hVar = new h();
        }
        m = hVar;
        if (th != null) {
            l.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        n = new Object();
    }

    public static CancellationException b(@Nullable String str, @Nullable Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    @NonNull
    public static <T> T c(@Nullable T t) {
        Objects.requireNonNull(t);
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [androidx.work.impl.utils.futures.AbstractFuture$b] */
    /* JADX WARN: Type inference failed for: r4v0, types: [androidx.work.impl.utils.futures.AbstractFuture<?>] */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.work.impl.utils.futures.AbstractFuture] */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.work.impl.utils.futures.AbstractFuture, androidx.work.impl.utils.futures.AbstractFuture<V>] */
    public static void e(AbstractFuture<?> abstractFuture) {
        e eVar = null;
        while (true) {
            abstractFuture.j();
            abstractFuture.afterDone();
            e d2 = abstractFuture.d(eVar);
            while (d2 != null) {
                eVar = d2.c;
                Runnable runnable = d2.f1841a;
                if (runnable instanceof g) {
                    g gVar = (g) runnable;
                    abstractFuture = gVar.h;
                    if (abstractFuture.h == gVar) {
                        if (m.b(abstractFuture, gVar, h(gVar.i))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    f(runnable, d2.b);
                }
                d2 = eVar;
            }
            return;
        }
    }

    public static void f(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e2) {
            Logger logger = l;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e2);
        }
    }

    public static Object h(ListenableFuture<?> listenableFuture) {
        if (listenableFuture instanceof AbstractFuture) {
            Object obj = ((AbstractFuture) listenableFuture).h;
            if (obj instanceof c) {
                c cVar = (c) obj;
                return cVar.f1839a ? cVar.b != null ? new c(false, cVar.b) : c.d : obj;
            }
            return obj;
        }
        boolean isCancelled = listenableFuture.isCancelled();
        if ((!k) & isCancelled) {
            return c.d;
        }
        try {
            Object i2 = i(listenableFuture);
            return i2 == null ? n : i2;
        } catch (CancellationException e2) {
            if (!isCancelled) {
                return new d(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture, e2));
            }
            return new c(false, e2);
        } catch (ExecutionException e3) {
            return new d(e3.getCause());
        } catch (Throwable th) {
            return new d(th);
        }
    }

    public static <V> V i(Future<V> future) throws ExecutionException {
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

    public final void a(StringBuilder sb) {
        try {
            Object i2 = i(this);
            sb.append("SUCCESS, result=[");
            sb.append(l(i2));
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

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        c(runnable);
        c(executor);
        e eVar = this.i;
        if (eVar != e.d) {
            e eVar2 = new e(runnable, executor);
            do {
                eVar2.c = eVar;
                if (m.a(this, eVar, eVar2)) {
                    return;
                }
                eVar = this.i;
            } while (eVar != e.d);
            f(runnable, executor);
        }
        f(runnable, executor);
    }

    public void afterDone() {
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
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
                e(abstractFuture);
                if (!(obj instanceof g)) {
                    return true;
                }
                ListenableFuture<? extends V> listenableFuture = ((g) obj).i;
                if (listenableFuture instanceof AbstractFuture) {
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

    public final e d(e eVar) {
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

    /* JADX WARN: Multi-variable type inference failed */
    public final V g(Object obj) throws ExecutionException {
        if (!(obj instanceof c)) {
            if (!(obj instanceof d)) {
                if (obj == n) {
                    return null;
                }
                return obj;
            }
            throw new ExecutionException(((d) obj).f1840a);
        }
        throw b("Task was cancelled.", ((c) obj).b);
    }

    @Override // java.util.concurrent.Future
    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        Locale locale;
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.h;
            if ((obj != null) & (!(obj instanceof g))) {
                return g(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                i iVar = this.j;
                if (iVar != i.c) {
                    i iVar2 = new i();
                    do {
                        iVar2.a(iVar);
                        if (m.c(this, iVar, iVar2)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.h;
                                    if ((obj2 != null) & (!(obj2 instanceof g))) {
                                        return g(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    k(iVar2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            k(iVar2);
                        } else {
                            iVar = this.j;
                        }
                    } while (iVar != i.c);
                    return g(this.h);
                }
                return g(this.h);
            }
            while (nanos > 0) {
                Object obj3 = this.h;
                if ((obj3 != null) & (!(obj3 instanceof g))) {
                    return g(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String abstractFuture = toString();
            String lowerCase = timeUnit.toString().toLowerCase(Locale.ROOT);
            String str = "Waited " + j + HexStringBuilder.DEFAULT_SEPARATOR + timeUnit.toString().toLowerCase(locale);
            if (nanos + 1000 < 0) {
                String str2 = str + " (plus ";
                long j2 = -nanos;
                long convert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
                long nanos2 = j2 - timeUnit.toNanos(convert);
                int i2 = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
                boolean z = i2 == 0 || nanos2 > 1000;
                if (i2 > 0) {
                    String str3 = str2 + convert + HexStringBuilder.DEFAULT_SEPARATOR + lowerCase;
                    if (z) {
                        str3 = str3 + Constants.SEPARATOR_COMMA;
                    }
                    str2 = str3 + HexStringBuilder.DEFAULT_SEPARATOR;
                }
                if (z) {
                    str2 = str2 + nanos2 + " nanoseconds ";
                }
                str = str2 + "delay)";
            }
            if (isDone()) {
                throw new TimeoutException(str + " but future completed as timeout expired");
            }
            throw new TimeoutException(str + " for " + abstractFuture);
        }
        throw new InterruptedException();
    }

    public void interruptTask() {
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.h instanceof c;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.h;
        return (!(obj instanceof g)) & (obj != null);
    }

    public final void j() {
        i iVar;
        do {
            iVar = this.j;
        } while (!m.c(this, iVar, i.c));
        while (iVar != null) {
            iVar.b();
            iVar = iVar.b;
        }
    }

    public final void k(i iVar) {
        iVar.f1843a = null;
        while (true) {
            i iVar2 = this.j;
            if (iVar2 == i.c) {
                return;
            }
            i iVar3 = null;
            while (iVar2 != null) {
                i iVar4 = iVar2.b;
                if (iVar2.f1843a != null) {
                    iVar3 = iVar2;
                } else if (iVar3 != null) {
                    iVar3.b = iVar4;
                    if (iVar3.f1843a == null) {
                        break;
                    }
                } else if (!m.c(this, iVar2, iVar4)) {
                    break;
                }
                iVar2 = iVar4;
            }
            return;
        }
    }

    public final String l(Object obj) {
        return obj == this ? "this future" : String.valueOf(obj);
    }

    @Nullable
    public String pendingToString() {
        Object obj = this.h;
        if (obj instanceof g) {
            return "setFuture=[" + l(((g) obj).i) + "]";
        } else if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        } else {
            return null;
        }
    }

    public boolean set(@Nullable V v) {
        if (v == null) {
            v = (V) n;
        }
        if (m.b(this, null, v)) {
            e(this);
            return true;
        }
        return false;
    }

    public boolean setException(Throwable th) {
        if (m.b(this, null, new d((Throwable) c(th)))) {
            e(this);
            return true;
        }
        return false;
    }

    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        d dVar;
        c(listenableFuture);
        Object obj = this.h;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (m.b(this, null, h(listenableFuture))) {
                    e(this);
                    return true;
                }
                return false;
            }
            g gVar = new g(this, listenableFuture);
            if (m.b(this, null, gVar)) {
                try {
                    listenableFuture.addListener(gVar, androidx.work.impl.utils.futures.a.INSTANCE);
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
            listenableFuture.cancel(((c) obj).f1839a);
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (isCancelled()) {
            sb.append(RequestStatus.CANCELLED);
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                str = pendingToString();
            } catch (RuntimeException e2) {
                str = "Exception thrown from implementation: " + e2.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public final boolean wasInterrupted() {
        Object obj = this.h;
        return (obj instanceof c) && ((c) obj).f1839a;
    }

    @Override // java.util.concurrent.Future
    public final V get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.h;
            if ((obj2 != null) & (!(obj2 instanceof g))) {
                return g(obj2);
            }
            i iVar = this.j;
            if (iVar != i.c) {
                i iVar2 = new i();
                do {
                    iVar2.a(iVar);
                    if (m.c(this, iVar, iVar2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.h;
                            } else {
                                k(iVar2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof g))));
                        return g(obj);
                    }
                    iVar = this.j;
                } while (iVar != i.c);
                return g(this.h);
            }
            return g(this.h);
        }
        throw new InterruptedException();
    }
}
