package com.coveiot.android.leonardo.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Spo2LastMeasuredData {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Double f4860a;
    @Nullable
    public final Long b;

    public Spo2LastMeasuredData(@Nullable Double d, @Nullable Long l) {
        this.f4860a = d;
        this.b = l;
    }

    public static /* synthetic */ Spo2LastMeasuredData copy$default(Spo2LastMeasuredData spo2LastMeasuredData, Double d, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            d = spo2LastMeasuredData.f4860a;
        }
        if ((i & 2) != 0) {
            l = spo2LastMeasuredData.b;
        }
        return spo2LastMeasuredData.copy(d, l);
    }

    @Nullable
    public final Double component1() {
        return this.f4860a;
    }

    @Nullable
    public final Long component2() {
        return this.b;
    }

    @NotNull
    public final Spo2LastMeasuredData copy(@Nullable Double d, @Nullable Long l) {
        return new Spo2LastMeasuredData(d, l);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Spo2LastMeasuredData) {
            Spo2LastMeasuredData spo2LastMeasuredData = (Spo2LastMeasuredData) obj;
            return Intrinsics.areEqual((Object) this.f4860a, (Object) spo2LastMeasuredData.f4860a) && Intrinsics.areEqual(this.b, spo2LastMeasuredData.b);
        }
        return false;
    }

    @Nullable
    public final Double getSpo2() {
        return this.f4860a;
    }

    @Nullable
    public final Long getTimeStamp() {
        return this.b;
    }

    public int hashCode() {
        Double d = this.f4860a;
        int hashCode = (d == null ? 0 : d.hashCode()) * 31;
        Long l = this.b;
        return hashCode + (l != null ? l.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Spo2LastMeasuredData(spo2=" + this.f4860a + ", timeStamp=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
