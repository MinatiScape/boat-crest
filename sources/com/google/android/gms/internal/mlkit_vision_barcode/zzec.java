package com.google.android.gms.internal.mlkit_vision_barcode;

import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.healthbuddies.RequestStatus;
import com.goodix.ble.libcomx.util.HexStringBuilder;
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
import javax.annotation.CheckForNull;
/* loaded from: classes9.dex */
public abstract class zzec<V> extends zzez implements zzev<V> {
    public static final boolean k;
    public static final Logger l;
    public static final h1 m;
    public static final Object n;
    @CheckForNull
    public volatile Object h;
    @CheckForNull
    public volatile k1 i;
    @CheckForNull
    public volatile q1 j;

    static {
        boolean z;
        Throwable th;
        Throwable th2;
        h1 n1Var;
        try {
            z = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z = false;
        }
        k = z;
        l = Logger.getLogger(zzec.class.getName());
        try {
            n1Var = new p1(null);
            th2 = null;
            th = null;
        } catch (Error | RuntimeException e) {
            try {
                th2 = e;
                n1Var = new l1(AtomicReferenceFieldUpdater.newUpdater(q1.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(q1.class, q1.class, "b"), AtomicReferenceFieldUpdater.newUpdater(zzec.class, q1.class, "j"), AtomicReferenceFieldUpdater.newUpdater(zzec.class, k1.class, "i"), AtomicReferenceFieldUpdater.newUpdater(zzec.class, Object.class, "h"));
                th = null;
            } catch (Error | RuntimeException e2) {
                th = e2;
                th2 = e;
                n1Var = new n1(null);
            }
        }
        m = n1Var;
        if (th != null) {
            Logger logger = l;
            Level level = Level.SEVERE;
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "UnsafeAtomicHelper is broken!", th2);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "<clinit>", "SafeAtomicHelper is broken!", th);
        }
        n = new Object();
    }

    public static Object h(zzev zzevVar) {
        Throwable zzf;
        if (zzevVar instanceof o1) {
            Object obj = ((zzec) zzevVar).h;
            if (obj instanceof i1) {
                i1 i1Var = (i1) obj;
                if (i1Var.f9416a) {
                    Throwable th = i1Var.b;
                    if (th != null) {
                        obj = new i1(false, th);
                    } else {
                        obj = i1.d;
                    }
                }
            }
            obj.getClass();
            return obj;
        } else if ((zzevVar instanceof zzez) && (zzf = ((zzez) zzevVar).zzf()) != null) {
            return new j1(zzf);
        } else {
            boolean isCancelled = zzevVar.isCancelled();
            if ((!k) & isCancelled) {
                i1 i1Var2 = i1.d;
                i1Var2.getClass();
                return i1Var2;
            }
            try {
                Object i = i(zzevVar);
                if (!isCancelled) {
                    return i == null ? n : i;
                }
                String valueOf = String.valueOf(zzevVar);
                return new i1(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: " + valueOf));
            } catch (Error e) {
                e = e;
                return new j1(e);
            } catch (CancellationException e2) {
                if (!isCancelled) {
                    return new j1(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: ".concat(String.valueOf(zzevVar)), e2));
                }
                return new i1(false, e2);
            } catch (RuntimeException e3) {
                e = e3;
                return new j1(e);
            } catch (ExecutionException e4) {
                if (isCancelled) {
                    return new i1(false, new IllegalArgumentException("get() did not throw CancellationException, despite reporting isCancelled() == true: ".concat(String.valueOf(zzevVar)), e4));
                }
                return new j1(e4.getCause());
            }
        }
    }

    public static Object i(Future future) throws ExecutionException {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
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
        return obj;
    }

    public static void m(zzec zzecVar, boolean z) {
        k1 k1Var = null;
        while (true) {
            for (q1 b = m.b(zzecVar, q1.c); b != null; b = b.b) {
                Thread thread = b.f9478a;
                if (thread != null) {
                    b.f9478a = null;
                    LockSupport.unpark(thread);
                }
            }
            zzecVar.zzk();
            k1 k1Var2 = k1Var;
            k1 a2 = m.a(zzecVar, k1.d);
            k1 k1Var3 = k1Var2;
            while (a2 != null) {
                k1 k1Var4 = a2.c;
                a2.c = k1Var3;
                k1Var3 = a2;
                a2 = k1Var4;
            }
            while (k1Var3 != null) {
                k1Var = k1Var3.c;
                Runnable runnable = k1Var3.f9432a;
                runnable.getClass();
                if (runnable instanceof m1) {
                    m1 m1Var = (m1) runnable;
                    zzecVar = m1Var.h;
                    if (zzecVar.h == m1Var) {
                        if (m.f(zzecVar, m1Var, h(m1Var.i))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = k1Var3.b;
                    executor.getClass();
                    n(runnable, executor);
                }
                k1Var3 = k1Var;
            }
            return;
        }
    }

    public static void n(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = l;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            logger.logp(level, "com.google.common.util.concurrent.AbstractFuture", "executeListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, (Throwable) e);
        }
    }

    public static final Object p(Object obj) throws ExecutionException {
        if (obj instanceof i1) {
            Throwable th = ((i1) obj).b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        } else if (obj instanceof j1) {
            throw new ExecutionException(((j1) obj).f9424a);
        } else {
            if (obj == n) {
                return null;
            }
            return obj;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:?, code lost:
        return true;
     */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean cancel(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.h
            boolean r1 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.m1
            r2 = 0
            r3 = 1
            if (r0 != 0) goto La
            r4 = r3
            goto Lb
        La:
            r4 = r2
        Lb:
            r1 = r1 | r4
            if (r1 == 0) goto L5f
            boolean r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzec.k
            if (r1 == 0) goto L1f
            com.google.android.gms.internal.mlkit_vision_barcode.i1 r1 = new com.google.android.gms.internal.mlkit_vision_barcode.i1
            java.util.concurrent.CancellationException r4 = new java.util.concurrent.CancellationException
            java.lang.String r5 = "Future.cancel() was called."
            r4.<init>(r5)
            r1.<init>(r8, r4)
            goto L29
        L1f:
            if (r8 == 0) goto L24
            com.google.android.gms.internal.mlkit_vision_barcode.i1 r1 = com.google.android.gms.internal.mlkit_vision_barcode.i1.c
            goto L26
        L24:
            com.google.android.gms.internal.mlkit_vision_barcode.i1 r1 = com.google.android.gms.internal.mlkit_vision_barcode.i1.d
        L26:
            r1.getClass()
        L29:
            r4 = r7
            r5 = r2
        L2b:
            com.google.android.gms.internal.mlkit_vision_barcode.h1 r6 = com.google.android.gms.internal.mlkit_vision_barcode.zzec.m
            boolean r6 = r6.f(r4, r0, r1)
            if (r6 == 0) goto L58
            m(r4, r8)
            boolean r4 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.m1
            if (r4 == 0) goto L56
            com.google.android.gms.internal.mlkit_vision_barcode.m1 r0 = (com.google.android.gms.internal.mlkit_vision_barcode.m1) r0
            com.google.android.gms.internal.mlkit_vision_barcode.zzev<? extends V> r0 = r0.i
            boolean r4 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.o1
            if (r4 == 0) goto L53
            r4 = r0
            com.google.android.gms.internal.mlkit_vision_barcode.zzec r4 = (com.google.android.gms.internal.mlkit_vision_barcode.zzec) r4
            java.lang.Object r0 = r4.h
            if (r0 != 0) goto L4b
            r5 = r3
            goto L4c
        L4b:
            r5 = r2
        L4c:
            boolean r6 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.m1
            r5 = r5 | r6
            if (r5 == 0) goto L56
            r5 = r3
            goto L2b
        L53:
            r0.cancel(r8)
        L56:
            r2 = r3
            goto L5f
        L58:
            java.lang.Object r0 = r4.h
            boolean r6 = r0 instanceof com.google.android.gms.internal.mlkit_vision_barcode.m1
            if (r6 != 0) goto L2b
            r2 = r5
        L5f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzec.cancel(boolean):boolean");
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException, ExecutionException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.h;
            if ((obj2 != null) && (!(obj2 instanceof m1))) {
                return p(obj2);
            }
            q1 q1Var = this.j;
            if (q1Var != q1.c) {
                q1 q1Var2 = new q1();
                do {
                    h1 h1Var = m;
                    h1Var.c(q1Var2, q1Var);
                    if (h1Var.g(this, q1Var, q1Var2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.h;
                            } else {
                                o(q1Var2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof m1))));
                        return p(obj);
                    }
                    q1Var = this.j;
                } while (q1Var != q1.c);
                Object obj3 = this.h;
                obj3.getClass();
                return p(obj3);
            }
            Object obj32 = this.h;
            obj32.getClass();
            return p(obj32);
        }
        throw new InterruptedException();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.h instanceof i1;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        Object obj = this.h;
        return (obj != null) & (!(obj instanceof m1));
    }

    public final void j(StringBuilder sb) {
        try {
            Object i = i(this);
            sb.append("SUCCESS, result=[");
            if (i == null) {
                sb.append("null");
            } else if (i == this) {
                sb.append("this future");
            } else {
                sb.append(i.getClass().getName());
                sb.append("@");
                sb.append(Integer.toHexString(System.identityHashCode(i)));
            }
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append(RequestStatus.CANCELLED);
        } catch (RuntimeException e) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        }
    }

    public final void k(StringBuilder sb) {
        String concat;
        int length = sb.length();
        sb.append("PENDING");
        Object obj = this.h;
        if (obj instanceof m1) {
            sb.append(", setFuture=[");
            l(sb, ((m1) obj).i);
            sb.append("]");
        } else {
            try {
                concat = zzbd.zza(zze());
            } catch (RuntimeException | StackOverflowError e) {
                concat = "Exception thrown from implementation: ".concat(String.valueOf(e.getClass()));
            }
            if (concat != null) {
                sb.append(", info=[");
                sb.append(concat);
                sb.append("]");
            }
        }
        if (isDone()) {
            sb.delete(length, sb.length());
            j(sb);
        }
    }

    public final void l(StringBuilder sb, @CheckForNull Object obj) {
        try {
            if (obj == this) {
                sb.append("this future");
            } else {
                sb.append(obj);
            }
        } catch (RuntimeException | StackOverflowError e) {
            sb.append("Exception thrown from implementation: ");
            sb.append(e.getClass());
        }
    }

    public final void o(q1 q1Var) {
        q1Var.f9478a = null;
        while (true) {
            q1 q1Var2 = this.j;
            if (q1Var2 != q1.c) {
                q1 q1Var3 = null;
                while (q1Var2 != null) {
                    q1 q1Var4 = q1Var2.b;
                    if (q1Var2.f9478a != null) {
                        q1Var3 = q1Var2;
                    } else if (q1Var3 != null) {
                        q1Var3.b = q1Var4;
                        if (q1Var3.f9478a == null) {
                            break;
                        }
                    } else if (!m.g(this, q1Var2, q1Var4)) {
                        break;
                    }
                    q1Var2 = q1Var4;
                }
                return;
            }
            return;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (getClass().getName().startsWith("com.google.common.util.concurrent.")) {
            sb.append(getClass().getSimpleName());
        } else {
            sb.append(getClass().getName());
        }
        sb.append('@');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[status=");
        if (this.h instanceof i1) {
            sb.append(RequestStatus.CANCELLED);
        } else if (isDone()) {
            j(sb);
        } else {
            k(sb);
        }
        sb.append("]");
        return sb.toString();
    }

    @CheckForNull
    public String zze() {
        if (this instanceof ScheduledFuture) {
            long delay = ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS);
            return "remaining delay=[" + delay + " ms]";
        }
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzez
    @CheckForNull
    public final Throwable zzf() {
        if (this instanceof o1) {
            Object obj = this.h;
            if (obj instanceof j1) {
                return ((j1) obj).f9424a;
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzev
    public final void zzj(Runnable runnable, Executor executor) {
        k1 k1Var;
        Objects.requireNonNull(executor, "Executor was null.");
        if (isDone() || (k1Var = this.i) == k1.d) {
            n(runnable, executor);
        }
        k1 k1Var2 = new k1(runnable, executor);
        do {
            k1Var2.c = k1Var;
            if (m.e(this, k1Var, k1Var2)) {
                return;
            }
            k1Var = this.i;
        } while (k1Var != k1.d);
        n(runnable, executor);
    }

    public void zzk() {
    }

    public final boolean zzl(Throwable th) {
        if (m.f(this, null, new j1(th))) {
            m(this, false);
            return true;
        }
        return false;
    }

    public final boolean zzm(zzev zzevVar) {
        Objects.requireNonNull(zzevVar);
        Object obj = this.h;
        if (obj != null) {
            if (obj instanceof i1) {
                i1 i1Var = (i1) obj;
            }
            return false;
        }
        if (m.f(this, null, h(zzevVar))) {
            m(this, false);
            return true;
        }
        return false;
    }

    public final boolean zzn() {
        Object obj = this.h;
        return (obj instanceof i1) && ((i1) obj).f9416a;
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException, ExecutionException {
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.h;
            boolean z = true;
            if ((obj != null) & (!(obj instanceof m1))) {
                return p(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
            if (nanos >= 1000) {
                q1 q1Var = this.j;
                if (q1Var != q1.c) {
                    q1 q1Var2 = new q1();
                    do {
                        h1 h1Var = m;
                        h1Var.c(q1Var2, q1Var);
                        if (h1Var.g(this, q1Var, q1Var2)) {
                            do {
                                LockSupport.parkNanos(this, Math.min(nanos, 2147483647999999999L));
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.h;
                                    if ((obj2 != null) & (!(obj2 instanceof m1))) {
                                        return p(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    o(q1Var2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= 1000);
                            o(q1Var2);
                        } else {
                            q1Var = this.j;
                        }
                    } while (q1Var != q1.c);
                    Object obj3 = this.h;
                    obj3.getClass();
                    return p(obj3);
                }
                Object obj32 = this.h;
                obj32.getClass();
                return p(obj32);
            }
            while (nanos > 0) {
                Object obj4 = this.h;
                if ((obj4 != null) & (!(obj4 instanceof m1))) {
                    return p(obj4);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            String zzecVar = toString();
            String timeUnit2 = timeUnit.toString();
            Locale locale = Locale.ROOT;
            String lowerCase = timeUnit2.toLowerCase(locale);
            String str = "Waited " + j + HexStringBuilder.DEFAULT_SEPARATOR + timeUnit.toString().toLowerCase(locale);
            if (nanos + 1000 < 0) {
                String concat = str.concat(" (plus ");
                long j2 = -nanos;
                long convert = timeUnit.convert(j2, TimeUnit.NANOSECONDS);
                long nanos2 = j2 - timeUnit.toNanos(convert);
                int i = (convert > 0L ? 1 : (convert == 0L ? 0 : -1));
                if (i != 0 && nanos2 <= 1000) {
                    z = false;
                }
                if (i > 0) {
                    String str2 = concat + convert + HexStringBuilder.DEFAULT_SEPARATOR + lowerCase;
                    if (z) {
                        str2 = str2.concat(Constants.SEPARATOR_COMMA);
                    }
                    concat = str2.concat(HexStringBuilder.DEFAULT_SEPARATOR);
                }
                if (z) {
                    concat = concat + nanos2 + " nanoseconds ";
                }
                str = concat.concat("delay)");
            }
            if (isDone()) {
                throw new TimeoutException(str.concat(" but future completed as timeout expired"));
            }
            throw new TimeoutException(str + " for " + zzecVar);
        }
        throw new InterruptedException();
    }
}
