package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public final class PairedStatsAccumulator {

    /* renamed from: a  reason: collision with root package name */
    public final StatsAccumulator f10703a = new StatsAccumulator();
    public final StatsAccumulator b = new StatsAccumulator();
    public double c = 0.0d;

    public static double a(double d) {
        return Doubles.constrainToRange(d, -1.0d, 1.0d);
    }

    public void add(double d, double d2) {
        this.f10703a.add(d);
        if (Doubles.isFinite(d) && Doubles.isFinite(d2)) {
            if (this.f10703a.count() > 1) {
                this.c += (d - this.f10703a.mean()) * (d2 - this.b.mean());
            }
        } else {
            this.c = Double.NaN;
        }
        this.b.add(d2);
    }

    public void addAll(PairedStats pairedStats) {
        if (pairedStats.count() == 0) {
            return;
        }
        this.f10703a.addAll(pairedStats.xStats());
        if (this.b.count() == 0) {
            this.c = pairedStats.sumOfProductsOfDeltas();
        } else {
            this.c += pairedStats.sumOfProductsOfDeltas() + ((pairedStats.xStats().mean() - this.f10703a.mean()) * (pairedStats.yStats().mean() - this.b.mean()) * pairedStats.count());
        }
        this.b.addAll(pairedStats.yStats());
    }

    public final double b(double d) {
        if (d > 0.0d) {
            return d;
        }
        return Double.MIN_VALUE;
    }

    public long count() {
        return this.f10703a.count();
    }

    public final LinearTransformation leastSquaresFit() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.c)) {
            return LinearTransformation.forNaN();
        }
        double c = this.f10703a.c();
        if (c > 0.0d) {
            if (this.b.c() > 0.0d) {
                return LinearTransformation.mapping(this.f10703a.mean(), this.b.mean()).withSlope(this.c / c);
            }
            return LinearTransformation.horizontal(this.b.mean());
        }
        Preconditions.checkState(this.b.c() > 0.0d);
        return LinearTransformation.vertical(this.f10703a.mean());
    }

    public final double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.c)) {
            return Double.NaN;
        }
        double c = this.f10703a.c();
        double c2 = this.b.c();
        Preconditions.checkState(c > 0.0d);
        Preconditions.checkState(c2 > 0.0d);
        return a(this.c / Math.sqrt(b(c * c2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.c / count();
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.c / (count() - 1);
    }

    public PairedStats snapshot() {
        return new PairedStats(this.f10703a.snapshot(), this.b.snapshot(), this.c);
    }

    public Stats xStats() {
        return this.f10703a.snapshot();
    }

    public Stats yStats() {
        return this.b.snapshot();
    }
}
