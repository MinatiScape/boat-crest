package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
@Beta
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class LinearTransformation {

    /* loaded from: classes10.dex */
    public static final class LinearTransformationBuilder {

        /* renamed from: a  reason: collision with root package name */
        public final double f10697a;
        public final double b;

        public LinearTransformation and(double d, double d2) {
            Preconditions.checkArgument(com.google.common.math.a.d(d) && com.google.common.math.a.d(d2));
            double d3 = this.f10697a;
            if (d == d3) {
                Preconditions.checkArgument(d2 != this.b);
                return new d(this.f10697a);
            }
            return withSlope((d2 - this.b) / (d - d3));
        }

        public LinearTransformation withSlope(double d) {
            Preconditions.checkArgument(!Double.isNaN(d));
            if (com.google.common.math.a.d(d)) {
                return new c(d, this.b - (this.f10697a * d));
            }
            return new d(this.f10697a);
        }

        public LinearTransformationBuilder(double d, double d2) {
            this.f10697a = d;
            this.b = d2;
        }
    }

    /* loaded from: classes10.dex */
    public static final class b extends LinearTransformation {

        /* renamed from: a  reason: collision with root package name */
        public static final b f10698a = new b();

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        public String toString() {
            return "NaN";
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d) {
            return Double.NaN;
        }
    }

    public static LinearTransformation forNaN() {
        return b.f10698a;
    }

    public static LinearTransformation horizontal(double d2) {
        Preconditions.checkArgument(com.google.common.math.a.d(d2));
        return new c(0.0d, d2);
    }

    public static LinearTransformationBuilder mapping(double d2, double d3) {
        Preconditions.checkArgument(com.google.common.math.a.d(d2) && com.google.common.math.a.d(d3));
        return new LinearTransformationBuilder(d2, d3);
    }

    public static LinearTransformation vertical(double d2) {
        Preconditions.checkArgument(com.google.common.math.a.d(d2));
        return new d(d2);
    }

    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d2);

    /* loaded from: classes10.dex */
    public static final class d extends LinearTransformation {

        /* renamed from: a  reason: collision with root package name */
        public final double f10700a;
        @LazyInit
        public LinearTransformation b;

        public d(double d) {
            this.f10700a = d;
            this.b = null;
        }

        public final LinearTransformation a() {
            return new c(0.0d, this.f10700a, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.b;
            if (linearTransformation == null) {
                LinearTransformation a2 = a();
                this.b = a2;
                return a2;
            }
            return linearTransformation;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.f10700a));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d) {
            throw new IllegalStateException();
        }

        public d(double d, LinearTransformation linearTransformation) {
            this.f10700a = d;
            this.b = linearTransformation;
        }
    }

    /* loaded from: classes10.dex */
    public static final class c extends LinearTransformation {

        /* renamed from: a  reason: collision with root package name */
        public final double f10699a;
        public final double b;
        @LazyInit
        public LinearTransformation c;

        public c(double d, double d2) {
            this.f10699a = d;
            this.b = d2;
            this.c = null;
        }

        public final LinearTransformation a() {
            double d = this.f10699a;
            if (d != 0.0d) {
                return new c(1.0d / d, (this.b * (-1.0d)) / d, this);
            }
            return new d(this.b, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.c;
            if (linearTransformation == null) {
                LinearTransformation a2 = a();
                this.c = a2;
                return a2;
            }
            return linearTransformation;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.f10699a == 0.0d;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.f10699a;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.f10699a), Double.valueOf(this.b));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d) {
            return (d * this.f10699a) + this.b;
        }

        public c(double d, double d2, LinearTransformation linearTransformation) {
            this.f10699a = d;
            this.b = d2;
            this.c = linearTransformation;
        }
    }
}
