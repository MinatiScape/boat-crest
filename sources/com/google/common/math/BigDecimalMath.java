package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import java.math.BigDecimal;
import java.math.RoundingMode;
@GwtIncompatible
/* loaded from: classes10.dex */
public class BigDecimalMath {

    /* loaded from: classes10.dex */
    public static class a extends c<BigDecimal> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f10689a = new a();

        @Override // com.google.common.math.c
        /* renamed from: f */
        public BigDecimal a(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            return bigDecimal.subtract(bigDecimal2);
        }

        @Override // com.google.common.math.c
        /* renamed from: g */
        public double c(BigDecimal bigDecimal) {
            return bigDecimal.doubleValue();
        }

        @Override // com.google.common.math.c
        /* renamed from: h */
        public int d(BigDecimal bigDecimal) {
            return bigDecimal.signum();
        }

        @Override // com.google.common.math.c
        /* renamed from: i */
        public BigDecimal e(double d, RoundingMode roundingMode) {
            return new BigDecimal(d);
        }
    }

    public static double roundToDouble(BigDecimal bigDecimal, RoundingMode roundingMode) {
        return a.f10689a.b(bigDecimal, roundingMode);
    }
}
