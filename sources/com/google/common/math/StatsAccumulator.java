package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.util.Iterator;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class StatsAccumulator {

    /* renamed from: a  reason: collision with root package name */
    public long f10707a = 0;
    public double b = 0.0d;
    public double c = 0.0d;
    public double d = Double.NaN;
    public double e = Double.NaN;

    public static double a(double d, double d2) {
        if (Doubles.isFinite(d)) {
            return d2;
        }
        if (Doubles.isFinite(d2) || d == d2) {
            return d;
        }
        return Double.NaN;
    }

    public void add(double d) {
        long j = this.f10707a;
        if (j == 0) {
            this.f10707a = 1L;
            this.b = d;
            this.d = d;
            this.e = d;
            if (Doubles.isFinite(d)) {
                return;
            }
            this.c = Double.NaN;
            return;
        }
        this.f10707a = j + 1;
        if (Doubles.isFinite(d) && Doubles.isFinite(this.b)) {
            double d2 = this.b;
            double d3 = d - d2;
            double d4 = d2 + (d3 / this.f10707a);
            this.b = d4;
            this.c += d3 * (d - d4);
        } else {
            this.b = a(this.b, d);
            this.c = Double.NaN;
        }
        this.d = Math.min(this.d, d);
        this.e = Math.max(this.e, d);
    }

    public void addAll(Iterable<? extends Number> iterable) {
        for (Number number : iterable) {
            add(number.doubleValue());
        }
    }

    public final void b(long j, double d, double d2, double d3, double d4) {
        long j2 = this.f10707a;
        if (j2 == 0) {
            this.f10707a = j;
            this.b = d;
            this.c = d2;
            this.d = d3;
            this.e = d4;
            return;
        }
        this.f10707a = j2 + j;
        if (Doubles.isFinite(this.b) && Doubles.isFinite(d)) {
            double d5 = this.b;
            double d6 = d - d5;
            double d7 = j;
            double d8 = d5 + ((d6 * d7) / this.f10707a);
            this.b = d8;
            this.c += d2 + (d6 * (d - d8) * d7);
        } else {
            this.b = a(this.b, d);
            this.c = Double.NaN;
        }
        this.d = Math.min(this.d, d3);
        this.e = Math.max(this.e, d4);
    }

    public double c() {
        return this.c;
    }

    public long count() {
        return this.f10707a;
    }

    public double max() {
        Preconditions.checkState(this.f10707a != 0);
        return this.e;
    }

    public double mean() {
        Preconditions.checkState(this.f10707a != 0);
        return this.b;
    }

    public double min() {
        Preconditions.checkState(this.f10707a != 0);
        return this.d;
    }

    public final double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public final double populationVariance() {
        Preconditions.checkState(this.f10707a != 0);
        if (Double.isNaN(this.c)) {
            return Double.NaN;
        }
        if (this.f10707a == 1) {
            return 0.0d;
        }
        return a.b(this.c) / this.f10707a;
    }

    public final double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public final double sampleVariance() {
        Preconditions.checkState(this.f10707a > 1);
        if (Double.isNaN(this.c)) {
            return Double.NaN;
        }
        return a.b(this.c) / (this.f10707a - 1);
    }

    public Stats snapshot() {
        return new Stats(this.f10707a, this.b, this.c, this.d, this.e);
    }

    public final double sum() {
        return this.b * this.f10707a;
    }

    public void addAll(Iterator<? extends Number> it) {
        while (it.hasNext()) {
            add(it.next().doubleValue());
        }
    }

    public void addAll(double... dArr) {
        for (double d : dArr) {
            add(d);
        }
    }

    public void addAll(int... iArr) {
        for (int i : iArr) {
            add(i);
        }
    }

    public void addAll(long... jArr) {
        for (long j : jArr) {
            add(j);
        }
    }

    public void addAll(Stats stats) {
        if (stats.count() == 0) {
            return;
        }
        b(stats.count(), stats.mean(), stats.sumOfSquaresOfDeltas(), stats.min(), stats.max());
    }

    public void addAll(StatsAccumulator statsAccumulator) {
        if (statsAccumulator.count() == 0) {
            return;
        }
        b(statsAccumulator.count(), statsAccumulator.mean(), statsAccumulator.c(), statsAccumulator.min(), statsAccumulator.max());
    }
}
