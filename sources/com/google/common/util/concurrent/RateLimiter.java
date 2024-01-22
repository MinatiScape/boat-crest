package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.u;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class RateLimiter {

    /* renamed from: a  reason: collision with root package name */
    public final a f10794a;
    @NullableDecl
    public volatile Object b;

    /* loaded from: classes10.dex */
    public static abstract class a {

        /* renamed from: com.google.common.util.concurrent.RateLimiter$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0525a extends a {

            /* renamed from: a  reason: collision with root package name */
            public final Stopwatch f10795a = Stopwatch.createStarted();

            @Override // com.google.common.util.concurrent.RateLimiter.a
            public long b() {
                return this.f10795a.elapsed(TimeUnit.MICROSECONDS);
            }

            @Override // com.google.common.util.concurrent.RateLimiter.a
            public void c(long j) {
                if (j > 0) {
                    Uninterruptibles.sleepUninterruptibly(j, TimeUnit.MICROSECONDS);
                }
            }
        }

        public static a a() {
            return new C0525a();
        }

        public abstract long b();

        public abstract void c(long j);
    }

    public RateLimiter(a aVar) {
        this.f10794a = (a) Preconditions.checkNotNull(aVar);
    }

    public static void b(int i) {
        Preconditions.checkArgument(i > 0, "Requested permits (%s) must be positive", i);
    }

    @VisibleForTesting
    public static RateLimiter c(double d, long j, TimeUnit timeUnit, double d2, a aVar) {
        u.c cVar = new u.c(aVar, j, timeUnit, d2);
        cVar.setRate(d);
        return cVar;
    }

    public static RateLimiter create(double d) {
        return d(d, a.a());
    }

    @VisibleForTesting
    public static RateLimiter d(double d, a aVar) {
        u.b bVar = new u.b(aVar, 1.0d);
        bVar.setRate(d);
        return bVar;
    }

    public final boolean a(long j, long j2) {
        return h(j) - j2 <= j;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    public abstract double e();

    public abstract void f(double d, long j);

    public final Object g() {
        Object obj = this.b;
        if (obj == null) {
            synchronized (this) {
                obj = this.b;
                if (obj == null) {
                    obj = new Object();
                    this.b = obj;
                }
            }
        }
        return obj;
    }

    public final double getRate() {
        double e;
        synchronized (g()) {
            e = e();
        }
        return e;
    }

    public abstract long h(long j);

    public final long i(int i) {
        long j;
        b(i);
        synchronized (g()) {
            j = j(i, this.f10794a.b());
        }
        return j;
    }

    public final long j(int i, long j) {
        return Math.max(k(i, j) - j, 0L);
    }

    public abstract long k(int i, long j);

    public final void setRate(double d) {
        Preconditions.checkArgument(d > 0.0d && !Double.isNaN(d), "rate must be positive");
        synchronized (g()) {
            f(d, this.f10794a.b());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    public boolean tryAcquire(long j, TimeUnit timeUnit) {
        return tryAcquire(1, j, timeUnit);
    }

    public static RateLimiter create(double d, long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "warmupPeriod must not be negative: %s", j);
        return c(d, j, timeUnit, 3.0d, a.a());
    }

    @CanIgnoreReturnValue
    public double acquire(int i) {
        long i2 = i(i);
        this.f10794a.c(i2);
        return (i2 * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    public boolean tryAcquire(int i) {
        return tryAcquire(i, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i, long j, TimeUnit timeUnit) {
        long max = Math.max(timeUnit.toMicros(j), 0L);
        b(i);
        synchronized (g()) {
            long b = this.f10794a.b();
            if (a(b, max)) {
                this.f10794a.c(j(i, b));
                return true;
            }
            return false;
        }
    }
}
