package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class u extends RateLimiter {
    public double c;
    public double d;
    public double e;
    public long f;

    /* loaded from: classes10.dex */
    public static final class b extends u {
        public final double g;

        public b(RateLimiter.a aVar, double d) {
            super(aVar);
            this.g = d;
        }

        @Override // com.google.common.util.concurrent.u
        public double l() {
            return this.e;
        }

        @Override // com.google.common.util.concurrent.u
        public void m(double d, double d2) {
            double d3 = this.d;
            double d4 = this.g * d;
            this.d = d4;
            if (d3 == Double.POSITIVE_INFINITY) {
                this.c = d4;
            } else {
                this.c = d3 != 0.0d ? (this.c * d4) / d3 : 0.0d;
            }
        }

        @Override // com.google.common.util.concurrent.u
        public long o(double d, double d2) {
            return 0L;
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends u {
        public final long g;
        public double h;
        public double i;
        public double j;

        public c(RateLimiter.a aVar, long j, TimeUnit timeUnit, double d) {
            super(aVar);
            this.g = timeUnit.toMicros(j);
            this.j = d;
        }

        @Override // com.google.common.util.concurrent.u
        public double l() {
            return this.g / this.d;
        }

        @Override // com.google.common.util.concurrent.u
        public void m(double d, double d2) {
            double d3 = this.d;
            double d4 = this.j * d2;
            long j = this.g;
            double d5 = (j * 0.5d) / d2;
            this.i = d5;
            double d6 = ((j * 2.0d) / (d2 + d4)) + d5;
            this.d = d6;
            this.h = (d4 - d2) / (d6 - d5);
            if (d3 == Double.POSITIVE_INFINITY) {
                this.c = 0.0d;
                return;
            }
            if (d3 != 0.0d) {
                d6 = (this.c * d6) / d3;
            }
            this.c = d6;
        }

        @Override // com.google.common.util.concurrent.u
        public long o(double d, double d2) {
            long j;
            double d3 = d - this.i;
            if (d3 > 0.0d) {
                double min = Math.min(d3, d2);
                j = (long) (((p(d3) + p(d3 - min)) * min) / 2.0d);
                d2 -= min;
            } else {
                j = 0;
            }
            return j + ((long) (this.e * d2));
        }

        public final double p(double d) {
            return this.e + (d * this.h);
        }
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final double e() {
        return TimeUnit.SECONDS.toMicros(1L) / this.e;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final void f(double d, long j) {
        n(j);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d;
        this.e = micros;
        m(d, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long h(long j) {
        return this.f;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long k(int i, long j) {
        n(j);
        long j2 = this.f;
        double d = i;
        double min = Math.min(d, this.c);
        this.f = LongMath.saturatedAdd(this.f, o(this.c, min) + ((long) ((d - min) * this.e)));
        this.c -= min;
        return j2;
    }

    public abstract double l();

    public abstract void m(double d, double d2);

    public void n(long j) {
        long j2 = this.f;
        if (j > j2) {
            this.c = Math.min(this.d, this.c + ((j - j2) / l()));
            this.f = j;
        }
    }

    public abstract long o(double d, double d2);

    public u(RateLimiter.a aVar) {
        super(aVar);
        this.f = 0L;
    }
}
